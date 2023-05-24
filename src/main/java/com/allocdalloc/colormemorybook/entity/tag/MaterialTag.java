package com.allocdalloc.colormemorybook.entity.tag;

import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookRegisterResponseDto;
import com.allocdalloc.colormemorybook.entity.material.Material;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "osogo_material_tag")
public class MaterialTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false)
    private Long id;

    @Column(name = "material_tag_name", nullable = false)
    private String tagName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @Builder
    public MaterialTag(String tagName, Material material) {
        this.tagName = tagName;
        this.material = material;
    }

    public static MaterialTag toEntity(String tagName, Material material) {
        return MaterialTag.builder()
                .tagName(tagName)
                .material(material)
                .build();
    }

    public static List<MaterialTag> toEntityList(List<String> tagList, Material material) {
        List<MaterialTag> materialTagEntityList = new ArrayList<>();
        tagList.forEach(tag -> materialTagEntityList.add(MaterialTag.toEntity(tag, material)));
        return materialTagEntityList;
    }

    public static List<ColorMemoryBookRegisterResponseDto.TagInfo> toDtoList(List<MaterialTag> materialTagList) {
        List<ColorMemoryBookRegisterResponseDto.TagInfo> tagDtoList = new ArrayList<>();
        materialTagList.forEach(tag -> tagDtoList.add(ColorMemoryBookRegisterResponseDto.TagInfo.builder()
                .tagId(tag.getId())
                .tagName(tag.getTagName())
                .build()));
        return tagDtoList;
    }
}
