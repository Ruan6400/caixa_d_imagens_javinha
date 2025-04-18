package com.subircargas.arquivo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;



@RestController
public class image_controller {
    
    @GetMapping("/images/{imagename}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String imagename) throws IOException{
        Path uploPath = Paths.get(System.getProperty("user.dir"),"uploads");
        Path filePath = uploPath.resolve(imagename).normalize();

        Resource resource = new UrlResource(filePath.toUri());
        if(resource.exists() && resource.isReadable()){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

}
