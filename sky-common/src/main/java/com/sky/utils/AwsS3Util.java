package com.sky.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Data
@AllArgsConstructor
@Slf4j
public class AwsS3Util {

    private String accessKeyId;
    private String accessKeySecret;
    private String region;
    private String bucketName;

    /**
     * Uploads a file to AWS S3
     *
     * @param bytes      File content in byte array
     * @param objectName The file name to be saved in S3
     * @return The public URL of the uploaded file
     */
    public String upload(byte[] bytes, String objectName) {
        // Initialize the S3 client
        S3Client s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, accessKeySecret)))
                .build();

        try {
            // Upload the file to S3
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectName)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(bytes));

        } catch (Exception e) {
            log.error("Error uploading file to AWS S3", e);
            throw new RuntimeException("Failed to upload file to AWS S3", e);
        } finally {
            // Close the S3 client
            s3Client.close();
        }

        // AWS S3 File URL format: https://{bucket}.s3.{region}.amazonaws.com/{file}
        String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, objectName);

        log.info("File uploaded to: {}", fileUrl);

        return fileUrl;
    }
}
