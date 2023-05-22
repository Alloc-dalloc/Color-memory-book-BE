package com.allocdalloc.colormemorybook.service;

import com.allocdalloc.colormemorybook.dto.image.response.ImageUploadResponseDto;
import com.allocdalloc.colormemorybook.util.AmazonS3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImageService {
    private final RekognitionClient rekognitionClient;
    private final AmazonS3Uploader amazonS3Uploader;


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ImageUploadResponseDto s3Upload(MultipartFile image) throws Exception {
        String s3FileName = amazonS3Uploader.makeS3FileName(image);
        String uploadImageUrl = amazonS3Uploader.saveFileAndGetUrl(image, s3FileName);
        return ImageUploadResponseDto.from(uploadImageUrl, detectLabels(s3FileName));      // 업로드된 파일의 S3 URL 주소 반환
    }

    private DetectLabelsResponse detectLabels(String fileName) {
        S3Object s3Object = S3Object.builder()
                .bucket(bucket)
                .name(fileName)
                .build();

        Image image = Image.builder()
                .s3Object(s3Object)
                .build();

        DetectLabelsRequest detectLabelsRequest = DetectLabelsRequest.builder()
                .image(image)
                .maxLabels(10)
                .build();

        DetectLabelsResponse response = rekognitionClient.detectLabels(detectLabelsRequest);
        response.labels().stream().map(Label::toString).forEach(log::info);

        return response;
    }
}
