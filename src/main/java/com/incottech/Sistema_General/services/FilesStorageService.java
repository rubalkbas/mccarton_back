package com.incottech.Sistema_General.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Rubén Vazquez Acosta
 *
 */
public interface FilesStorageService {
	
	 public void init(String path);

	  public void save(MultipartFile file, String path);

	  public Resource load(String filename,String path);

	  public void deleteAll(String path);

	  public Stream<Path> loadAll(String pathTemp);

}

