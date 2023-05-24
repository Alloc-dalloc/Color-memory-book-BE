package com.allocdalloc.colormemorybook.dto.colorMemoryBook.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ColorMemoryBookRegisterResponseDto {
    private Long materialId;
    private String imageUrl;
    private List<TagInfo> tagList;
    private String description;

    @Getter
    public static class TagInfo{
        private Long tagId;
        private String tagName;

        @Builder
        public TagInfo(Long tagId, String tagName){
            this.tagId = tagId;
            this.tagName = tagName;
        }
    }

    @Builder
    public ColorMemoryBookRegisterResponseDto(Long materialId, String imageUrl, List<TagInfo> tagList, String description){
        this.materialId = materialId;
        this.imageUrl = imageUrl;
        this.tagList = tagList;
        this.description = description;
    }
}
