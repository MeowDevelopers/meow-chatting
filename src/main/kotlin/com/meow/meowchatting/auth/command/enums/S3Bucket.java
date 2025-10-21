package com.meow.meowchatting.auth.command.enums;

import lombok.Getter;

@Getter
public enum S3Bucket {
    UserProfileBucket("user_profile_bucket", "user-profile/");

    private final String bucketName;
    private final String s3Path;

    S3Bucket(String bucketName, String s3Path) {
        this.bucketName = bucketName;
        this.s3Path = s3Path;
    }

    public String getS3Path(String fileName, String active){
        return s3Path + fileName;
    }

    public String getUserProfileS3Path(String fileName, Long userId, String imagePrefix){
        return s3Path + imagePrefix + "/" + userId + "/" + fileName;
    }
}
