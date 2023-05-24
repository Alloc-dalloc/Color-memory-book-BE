package com.allocdalloc.colormemorybook.dto.image.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Label;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ImageTotalAnalysisResponseDto {
    private String imageUrl;
    private List<LabelDto> labels;
    private List<ImageColorAnalysisResponseDto> colorAnalysis;
    @Builder
    public ImageTotalAnalysisResponseDto(String imageUrl, List<LabelDto> labels, List<ImageColorAnalysisResponseDto> colorAnalysis) {
        this.imageUrl = imageUrl;
        this.labels = labels;
        this.colorAnalysis = colorAnalysis;
    }


    public static ImageTotalAnalysisResponseDto from(String imageUrl, DetectLabelsResponse detectLabelsResponse, List<ImageColorAnalysisResponseDto> imageColorAnalysisResponseDto) {
        return ImageTotalAnalysisResponseDto.builder()
                .imageUrl(imageUrl)
                .labels(detectLabelsResponse.labels().stream().map(LabelDto::from).collect(Collectors.toList()))
                .colorAnalysis(imageColorAnalysisResponseDto)
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
