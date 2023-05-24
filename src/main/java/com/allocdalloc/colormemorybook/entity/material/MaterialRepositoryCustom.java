package com.allocdalloc.colormemorybook.entity.material;

import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookDetailInfoResponseDto;
import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookHomeResponse;
import com.allocdalloc.colormemorybook.entity.user.detail.UserAccount;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MaterialRepositoryCustom {
    List<ColorMemoryBookHomeResponse> findByKeyword(UserAccount userAccount, Long cursor, Pageable pageable, String keyword);
    Optional<ColorMemoryBookDetailInfoResponseDto> findByIdAndUserId(Long id, UserAccount userAccount);
}
