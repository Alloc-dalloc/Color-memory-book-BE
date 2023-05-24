package com.allocdalloc.colormemorybook.entity.material;

import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookDetailInfoResponseDto;
import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookHomeResponse;
import com.allocdalloc.colormemorybook.entity.user.detail.UserAccount;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.allocdalloc.colormemorybook.entity.material.QMaterial.material;
import static com.allocdalloc.colormemorybook.entity.tag.QMaterialTag.materialTag;

@Repository
@RequiredArgsConstructor
public class MaterialRepositoryCustomImpl implements MaterialRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ColorMemoryBookHomeResponse> findByKeyword(UserAccount userAccount, Long cursor, Pageable pageable, String keyword) {
        List<ColorMemoryBookHomeResponse> result = jpaQueryFactory
                .select(Projections.fields(ColorMemoryBookHomeResponse.class, material.id, material.imageUrl))
                .from(material)
                .leftJoin(materialTag).on(material.id.eq(materialTag.material.id)).fetchJoin()
                .where(
                        eqMemberId(userAccount.getUserId()),
                        ltMaterialId(cursor),
                        containsKeyword(keyword)
                )
                .distinct()
                .orderBy(material.createdAt.desc())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public Optional<ColorMemoryBookDetailInfoResponseDto> findByIdAndUserId(Long id, UserAccount userAccount) {
        // First, get the basic information of the Material.
        ColorMemoryBookDetailInfoResponseDto basicInfo = jpaQueryFactory
                .select(Projections.constructor(
                        ColorMemoryBookDetailInfoResponseDto.class,
                        material.id,
                        material.imageUrl,
                        material.description
                ))
                .from(material)
                .where(material.id.eq(id), eqMemberId(userAccount.getUserId()))
                .fetchOne();

        // Then, get the tags of the Material using a separate query.
        List<ColorMemoryBookDetailInfoResponseDto.TagInfo> tags = jpaQueryFactory
                .select(Projections.constructor(
                        ColorMemoryBookDetailInfoResponseDto.TagInfo.class,
                        material.id,
                        materialTag.tagName
                ))
                .from(materialTag)
                .where(materialTag.material.id.eq(id))
                .fetch();

        // Set the tags to the basic information.
        basicInfo.setTags(tags);

        return Optional.of(basicInfo);


    }


    // BooleanExpression Methods
    private BooleanExpression eqMemberId(Long userId) {
        if (userId == null) {
            return null;
        }
        return material.member.id.eq(userId);
    }
    private BooleanExpression ltMaterialId(Long cursorId) {
        return cursorId == null ? null : material.id.lt(cursorId);
    }

    private BooleanExpression containsKeyword(String keyword) {
        if (keyword == null) {
            return null;
        }
        return material.description.contains(keyword)
                .or(materialTag.tagName.contains(keyword));
    }
}
