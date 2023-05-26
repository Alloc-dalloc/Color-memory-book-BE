package com.allocdalloc.colormemorybook.service;

import com.allocdalloc.colormemorybook.dto.colorMemoryBook.request.ColorMemoryBookRegisterRequestDto;
import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookDetailInfoResponseDto;
import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookHomeResponse;
import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookRegisterResponseDto;
import com.allocdalloc.colormemorybook.entity.material.Material;
import com.allocdalloc.colormemorybook.entity.material.MaterialRepository;
import com.allocdalloc.colormemorybook.entity.tag.MaterialTag;
import com.allocdalloc.colormemorybook.entity.tag.MaterialTagRepository;
import com.allocdalloc.colormemorybook.entity.user.Member;
import com.allocdalloc.colormemorybook.entity.user.MemberRepository;
import com.allocdalloc.colormemorybook.entity.user.detail.UserAccount;
import com.allocdalloc.colormemorybook.exception.custom.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ColorMemoryBookService {
    private final MemberRepository memberRepository;
    private final MaterialRepository materialRepository;
    private final MaterialTagRepository materialTagRepository;

    @Transactional
    public ColorMemoryBookRegisterResponseDto registerColorMemoryBook(UserAccount userAccount, ColorMemoryBookRegisterRequestDto colorMemoryBookRegisterRequestDto) {
        Member member = memberRepository.findByEmail(userAccount.getEmail())
                .orElseThrow(() -> CustomException.builder().httpStatus(HttpStatus.NOT_FOUND).message("존재하지 않는 유저입니다.").build());

        Material material = materialRepository.save(Material.toEntity(colorMemoryBookRegisterRequestDto, member));

        List<MaterialTag> materialTagList = materialTagRepository.saveAll(MaterialTag.toEntityList(colorMemoryBookRegisterRequestDto.getTagList(), material));

        return ColorMemoryBookRegisterResponseDto.builder()
                .imageUrl(material.getImageUrl())
                .materialId(material.getId())
                .tagList(MaterialTag.toDtoList(materialTagList))
                .build();
    }

    public List<ColorMemoryBookHomeResponse> getMaterialAtHome(UserAccount userAccount, Long cursor, Pageable pageable, String keyword){
        return materialRepository.findByKeyword(userAccount, cursor, pageable, keyword);
    }

    public ColorMemoryBookDetailInfoResponseDto getMaterialDetailInfo(UserAccount userAccount, Long materialId){
        return materialRepository.findByIdAndUserId(materialId, userAccount).get();
    }
}
