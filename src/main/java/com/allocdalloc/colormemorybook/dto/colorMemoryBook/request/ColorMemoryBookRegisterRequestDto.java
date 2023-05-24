package com.allocdalloc.colormemorybook.dto.colorMemoryBook.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ColorMemoryBookRegisterRequestDto {
    private String imageUrl;
    private List<String> tagList;
    private String description;
}
