package com.toyshop.StoreToys_API.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

    private final String folder = "src/main/resources/static/uploads";

    @GetMapping("/img/{name}")
    public ResponseEntity<?> getImg(@PathVariable String name) throws MalformedURLException {
        Path imgPath = Paths.get(folder, name).normalize();
        Resource resource = new UrlResource(imgPath.toUri());
        if(resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
        } else {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(
                    new UrlResource(Paths.get(folder,"img.png").toUri()));
        }
    }
}
