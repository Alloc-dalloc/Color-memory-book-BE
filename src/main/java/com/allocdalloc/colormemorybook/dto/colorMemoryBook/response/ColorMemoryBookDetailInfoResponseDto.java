package com.allocdalloc.colormemorybook.dto.colorMemoryBook.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ColorMemoryBookDetailInfoResponseDto {
    private Long id;
    private String imageUrl;
    private String description;
    private List<TagInfo> tags;

    @Getter
    @Setter
    public static class TagInfo{
        private Long id;
        private String tagName;

        @Builder
        public TagInfo(Long id, String tagName){
            this.id = id;
            this.tagName = tagName;
        }
    }

    @Builder
    public ColorMemoryBookDetailInfoResponseDto(Long id, String imageUrl, String description) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.description = description;
    }

}
