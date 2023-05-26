package com.allocdalloc.colormemorybook.entity.material;

import com.allocdalloc.colormemorybook.dto.colorMemoryBook.request.ColorMemoryBookRegisterRequestDto;
import com.allocdalloc.colormemorybook.entity.tag.MaterialTag;
import com.allocdalloc.colormemorybook.entity.user.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "osogo_material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", nullable = false)
    private Long id;

    @Column(name = "material_image_url")
    private String imageUrl;
    @Column(name = "material_description")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "material")
    private List<MaterialTag> materialTagList;

    @Builder
    public Material(String imageUrl, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Member member) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = member;
    }

    public static Material toEntity(ColorMemoryBookRegisterRequestDto colorMemoryBookRegisterRequestDto, Member member) {
        return Material.builder()
                .imageUrl(colorMemoryBookRegisterRequestDto.getImageUrl())
                .description(colorMemoryBookRegisterRequestDto.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .member(member)
                .build();
    }
}
