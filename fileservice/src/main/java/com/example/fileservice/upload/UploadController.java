package com.example.fileservice.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class UploadController {
    @Value("${file}")
    private String fileDirectory;
    @PostMapping("/upload")
    public ResponseEntity saveFile(@RequestParam MultipartFile file) throws IOException {
        //
        if (!file.isEmpty()) {
            String downloadPath = fileDirectory + file.getOriginalFilename();
            file.transferTo(new File(downloadPath));
            return ResponseEntity.status(HttpStatus.OK).body(downloadPath);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("file is null");
    }
}
