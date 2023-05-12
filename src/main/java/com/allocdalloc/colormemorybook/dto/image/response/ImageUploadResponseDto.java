package com.allocdalloc.colormemorybook.dto.image.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ImageUploadResponseDto {
    private String imageUrl;
    // TODO: 이미지 관련 로직 후 DTO 업데이트 필요

    @Builder
    public ImageUploadResponseDto(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public static ImageUploadResponseDto from(String imageUrl) {
        return ImageUploadResponseDto.builder()
                .imageUrl(imageUrl)
                .build();
    }
}
