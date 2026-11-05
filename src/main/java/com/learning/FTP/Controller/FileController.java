package com.learning.FTP.Controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile[] files) {

        Path uploadPath = Paths.get("uploads");

        try {
            Files.createDirectories(uploadPath);

            for (MultipartFile file : files) {

                String storedFileName =
                        UUID.randomUUID() + "_" + file.getOriginalFilename();

                Path filePath = uploadPath.resolve(storedFileName);

                Files.write(filePath, file.getBytes());
            }

            return files.length + " file(s) uploaded successfully";

        } catch (IOException e) {
            return "File upload failed";
        }
    }
}