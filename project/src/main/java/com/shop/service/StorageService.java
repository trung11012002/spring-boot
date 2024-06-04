package com.shop.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();
    String store(MultipartFile file);

    void deleteAll();
    void delete(Long id);
}
