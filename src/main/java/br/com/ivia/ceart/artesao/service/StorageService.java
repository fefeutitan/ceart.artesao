package br.com.ivia.ceart.artesao.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.ivia.ceart.artesao.util.exception.ArtesaoException;

@Service
public class StorageService {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = Paths.get("/./upload-dir");

	public void store(MultipartFile file, String newFileName) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new ArtesaoException("FAIL!");
		}
	}

	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new ArtesaoException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new ArtesaoException("FAIL!!");
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
	
	public boolean delete(String nomeFile) {
		Path file = rootLocation.resolve(nomeFile);
		return file.toFile().delete();
	}

	public void init() {
		try {
			if(Files.notExists(rootLocation)) {
				Files.createDirectory(rootLocation);
			}
		} catch (IOException e) {
			throw new ArtesaoException(e.getMessage());
		}
	}
}
