package com.allocdalloc.colormemorybook.service;

import com.allocdalloc.colormemorybook.dto.image.response.ImageColorAnalysisResponseDto;
import com.allocdalloc.colormemorybook.dto.image.response.ImageTotalAnalysisResponseDto;
import com.allocdalloc.colormemorybook.util.AmazonS3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImageService {
    private final RekognitionClient rekognitionClient;
    private final AmazonS3Uploader amazonS3Uploader;
    private final RestTemplate restTemplate;


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ImageTotalAnalysisResponseDto imageAnalysis(MultipartFile image) throws Exception {
        String s3FileName = amazonS3Uploader.makeS3FileName(image);
        String uploadImageUrl = amazonS3Uploader.saveFileAndGetUrl(image, s3FileName);
        return ImageTotalAnalysisResponseDto.from(uploadImageUrl, detectLabels(s3FileName), analysisColor(uploadImageUrl));      // 업로드된 파일의 S3 URL 주소 반환
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

    private List<ImageColorAnalysisResponseDto> analysisColor(String fileName) {
        // Request Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Request Body 설정
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("image", fileName);


        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<List<ImageColorAnalysisResponseDto>> response = restTemplate.exchange(
                "/color",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<List<ImageColorAnalysisResponseDto>>() {}
        );
        return response.getBody();
    }

}
