package com.incottech.Sistema_General.services;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Rubén Vazquez Acosta
 *
 */
@Service
public class FilesStorageServiceImpl implements FilesStorageService {
	
	
	
	
	  @Override
	  public void init(String path) {
	    try {
	      final Path root = Paths.get(path);
	      Files.createDirectory(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

	  @Override
	  public void save(MultipartFile file, String path) {
	    try {
	      final Path root = Paths.get(path);
	      Files.copy(file.getInputStream(), root.resolve(path));
	 
	      
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }

	  @Override
	  public Resource load(String filename,String path) {
	    try {
	      final Path root = Paths.get(path);
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  @Override
	  public void deleteAll(String path) {
		final Path root = Paths.get(path);
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }

	  @Override
	  public Stream<Path> loadAll(String pathTemp) {
	    try {
	    final Path root = Paths.get(pathTemp);
	      return Files.walk(root, 1).filter(path -> !path.equals(root)).map(root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
	
	

}
