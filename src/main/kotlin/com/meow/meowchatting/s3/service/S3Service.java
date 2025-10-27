package com.meow.meowchatting.s3.service;

import com.meow.meowchatting.s3.config.AwsConfig;
import com.meow.meowchatting.s3.enums.S3Bucket;
import com.meow.meowchatting.s3.enums.S3UploadCode;
import com.meow.meowchatting.s3.exception.AwsS3Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.io.InputStream;


@Slf4j
@Service
public class S3Service {
    private final AwsConfig awsConfig;
    private final S3Client s3Client;

    public S3Service(AwsConfig awsConfig, S3Client s3Client) {
        this.awsConfig = awsConfig;
        this.s3Client = s3Client;
    }

    /* *
     * 이미지 업로드
     */
    public String uploadFile(MultipartFile multipartFile, S3Bucket s3Bucket){
        String savePath = s3Bucket.getS3Path(multipartFile.getOriginalFilename(), awsConfig.getActive());
        return uploadFile(multipartFile, s3Bucket, savePath);
    }

    public String uploadFile(MultipartFile multipartFile, S3Bucket s3Bucket, String savePath) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            PutObjectRequest request = PutObjectRequest.builder()
                    .contentType(multipartFile.getContentType())
                    .bucket(s3Bucket.getBucketName())
                    .key(savePath)
                    .build();

            PutObjectResponse response = s3Client.putObject(request, RequestBody.fromInputStream(inputStream, multipartFile.getSize()));

            if (!response.sdkHttpResponse().isSuccessful()) {
                log.error("S3 업로드하다가 실패함 : {}", response.sdkHttpResponse());
                throw new AwsS3Exception(S3UploadCode.AWS_S3_UPLOAD_FAIL);
            }

            return savePath;
        } catch (IOException e) {
            throw new AwsS3Exception(S3UploadCode.AWS_S3_UPLOAD_FAIL);
        }
    }

    public void uploadFile(S3Bucket s3Bucket, String key, byte[] fileBytes, String contentType) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(s3Bucket.getBucketName())
                .key(key)
                .contentType(contentType != null ? contentType : "application/octet-stream")
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileBytes));
    }

    public byte[] downloadFile(S3Bucket s3Bucket, String pathName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(s3Bucket.getBucketName())
                .key(pathName)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
        return objectBytes.asByteArray();
    }
}
