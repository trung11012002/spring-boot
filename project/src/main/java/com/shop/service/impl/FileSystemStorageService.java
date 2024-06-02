package com.shop.service.impl;

import com.shop.model.Product;
import com.shop.service.ProductService;
import com.shop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {
    @Autowired
    private ProductService productService;

    private final Path rootLocation;

    public FileSystemStorageService() {
        this.rootLocation = Paths.get("src/main/resources/static/uploads");
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String store(MultipartFile file) {
        try {
            // Lấy tên file gốc
            String originalFilename = file.getOriginalFilename();

            // Tách phần mở rộng của file
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                int dotIndex = originalFilename.lastIndexOf(".");
                extension = originalFilename.substring(dotIndex);
                originalFilename = originalFilename.substring(0, dotIndex);
            }

            // Tạo tên file mới với UUID
            String newFilename = originalFilename + "-" + UUID.randomUUID().toString() + extension;

            // Xác định đường dẫn đích cho file mới
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(newFilename))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            // Trả về tên file mới
            return newFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteAll() {
        // Implement logic if needed
    }

    @Override
    public void delete(int id) {
        Product product = productService.find(id);
        try {
            Path file = rootLocation.resolve(product.getImage()).normalize().toAbsolutePath();
            Files.deleteIfExists(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
