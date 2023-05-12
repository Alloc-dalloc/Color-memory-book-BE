package com.allocdalloc.colormemorybook.dto.image.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Label;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ImageUploadResponseDto {
    private String imageUrl;
    private List<LabelDto> labels;
    // TODO: 이미지 관련 로직 후 DTO 업데이트 필요

    @Builder
    public ImageUploadResponseDto(String imageUrl, List<LabelDto> labels) {
        this.imageUrl = imageUrl;
        this.labels = labels;
    }


    public static ImageUploadResponseDto from(String imageUrl, DetectLabelsResponse detectLabelsResponse) {
        return ImageUploadResponseDto.builder()
                .imageUrl(imageUrl)
                .labels(detectLabelsResponse.labels().stream().map(LabelDto::from).collect(Collectors.toList()))
                .build();
    }

    @Getter
    @NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
    static class LabelDto {
        private String name;
        private Float confidence;

        @Builder
        public LabelDto(String name, Float confidence) {
            this.name = name;
            this.confidence = confidence;
        }

        public static LabelDto from(Label label) {
            return LabelDto.builder()
                    .name(label.name())
                    .confidence(label.confidence())
                    .build();
        }
    }
}
