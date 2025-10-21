package com.meow.meowchatting.s3.service;

import org.springframework.stereotype.Service;

@Service
public class S3ProfileService {
    private final S3Service s3Service;

    public S3ProfileService(S3Service s3Service) {
        this.s3Service = s3Service;
    }
}
