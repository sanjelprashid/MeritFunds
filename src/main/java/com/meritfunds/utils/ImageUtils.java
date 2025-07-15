package com.meritfunds.utils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

public class ImageUtils {

    public static void saveImage(InputStream uploadedInputStream,String uploadPath,String fileName) {
        try {
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path filePath = uploadDir.resolve(fileName);
            Files.copy(uploadedInputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            // Consider logging the exception or re-throwing it as a custom exception
            throw new RuntimeException("Error saving image: " + e.getMessage(), e);
        }
    }
}