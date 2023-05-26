package com.allocdalloc.colormemorybook.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmazonS3Uploader {
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${image.path}")
    private String imagePath;

    public String makeS3FileName(MultipartFile multipartFile) throws Exception {
        return imagePath + UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
    }

    public String saveFileAndGetUrl(MultipartFile multipartFile, String s3FileName) throws Exception {

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentType(multipartFile.getContentType());
        objMeta.setContentLength(multipartFile.getSize());

        amazonS3.putObject(new PutObjectRequest(bucket, s3FileName, multipartFile.getInputStream(), objMeta)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    public void deleteFile(String uuidFileName) {
        try {
            String keyName = imagePath + uuidFileName;
            boolean isObjectExist = amazonS3.doesObjectExist(bucket, keyName);
            if (isObjectExist) {
                amazonS3.deleteObject(bucket, keyName);
            }
        } catch (Exception e) {
            log.debug("Delete File failed", e);
        }
    }
}
