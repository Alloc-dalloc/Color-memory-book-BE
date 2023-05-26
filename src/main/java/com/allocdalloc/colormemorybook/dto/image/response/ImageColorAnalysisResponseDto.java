package com.allocdalloc.colormemorybook.dto.image.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageColorAnalysisResponseDto {
    private String colorName;
    private int colorPercentage;

    @Builder
    public ImageColorAnalysisResponseDto(String colorName, int colorPercentage) {
        this.colorName = colorName;
        this.colorPercentage = colorPercentage;
    }
}
