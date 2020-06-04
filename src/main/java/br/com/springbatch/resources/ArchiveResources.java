package br.com.springbatch.resources;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ArchiveResources {
	
	private static final String uploadingDir = System.getProperty("user.dir") + "/data/in/";
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> uploadingPost(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {
        for(MultipartFile uploadedFile : uploadingFiles) {
        	try {
	        	File file = new File(uploadingDir + uploadedFile.getOriginalFilename());
	            uploadedFile.transferTo(file);
	        } catch (IOException e) {
				throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
			}
        }
        return ResponseEntity.ok().body("Arquivo(s) enviado(s) com sucesso!");
    }

}
