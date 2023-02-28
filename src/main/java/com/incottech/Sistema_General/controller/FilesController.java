package com.incottech.Sistema_General.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.incottech.Sistema_General.model.FileInfo;
import com.incottech.Sistema_General.model.ResponseMessage;
import com.incottech.Sistema_General.services.FilesStorageService;



/**
 * @author Rubén Vazquez Acosta
 *
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/archivos")
public class FilesController {
	
	@Value("${sistemaglobal.app.pathTemp}")
	private String pathTemp;
	
	@Value("${sistemaglobal.app.pathDest}")
	private String pathDest;
	
	@Value("${sistemaglobal.app.pathBd}")
	private String pathBd;
	
	
	
	
	@Autowired
	  FilesStorageService storageService;
	
	

	  @GetMapping("/files")
	  public ResponseEntity<List<FileInfo>> getListFiles() {
	    List<FileInfo> fileInfos = storageService.loadAll(pathTemp).map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

	      return new FileInfo(filename, url);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }

	  @GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = storageService.load(filename,pathTemp);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	
	  @PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String nombreArchivo = String.valueOf(timestamp.getTime()) + file.getOriginalFilename(); 
		
	    InputStream is = null; 
	    OutputStream os = null;

	    try {
	     // storageService.deleteAll();
	      storageService.save(file,pathTemp + nombreArchivo);
	      is = new FileInputStream(pathTemp + nombreArchivo); 
	      os = new FileOutputStream(pathDest + nombreArchivo ); 
	      
	      
	      byte[] buf = new byte[100024];
	      
	      int bytesRead; 
	      while ((bytesRead = is.read(buf)) > 0) 
	      { os.write(buf, 0, bytesRead
	    		  );

	      }
	      
	      File scriptfile = new File(pathDest + nombreArchivo);
	      scriptfile.setReadable(true);
		    scriptfile.setWritable(true);
		    scriptfile.setExecutable(true);
	      is.close();
          os.close();
          
          String op = System.getProperty("os.name").toLowerCase();
		    
		    if( !op.contains("windows") ) {
		    	 message = "entramos y otorgamos permisos al archivo: " + pathBd + nombreArchivo + "!";
		    	Runtime.getRuntime().exec("chmod 777 " + pathDest + nombreArchivo);
		    }
          
          
	      message = pathBd + nombreArchivo;
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "El archivo no pudo ser subido: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }

}


