package com.allocdalloc.colormemorybook.dto.colorMemoryBook.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ColorMemoryBookHomeResponse {
    private Long id;
    private String imageUrl;

    @Builder
    public ColorMemoryBookHomeResponse(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
