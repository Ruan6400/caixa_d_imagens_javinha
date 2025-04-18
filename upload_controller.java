package com.subircargas.arquivo.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class upload_controller {

    private static final String uploadDir = System.getProperty("user.dir")+File.separator+"/uploads";

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file){
        try{
            File diretorio = new File(uploadDir);
            if(!diretorio.exists()){
                diretorio.mkdirs();
            }

            File destino = new File(diretorio,file.getOriginalFilename());
            System.out.println("Salvando em: " + destino.getAbsolutePath());
            file.transferTo(destino);

            return "Arquivo Salvo";
        } catch (IOException e){
            return "Erro ao fazer o upload +"+e.getMessage();
        }
    }
    
}


