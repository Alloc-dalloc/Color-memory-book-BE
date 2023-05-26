package com.allocdalloc.colormemorybook.dto.image.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageColorAnalysisRequestDto {
    private String s3ImageUrl;

    @Builder
    public ImageColorAnalysisRequestDto(String s3ImageUrl) {
        this.s3ImageUrl = s3ImageUrl;
    }
}
