package com.project.shopapp.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUtil {
    public static String storeFile(MultipartFile file) throws IOException {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        // Add UUID to start of string make file name to be unique name
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        //Declare save file path
        java.nio.file.Path uploadDir = Paths.get("uploads");

        // Check if folder dose not exist create new one
        if (!Files.exists(uploadDir))
            Files.createDirectories(uploadDir);

        // Create destination for file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFileName);

        // Copy file to destination if file already exist create REPLACE_EXISTING
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
}
