package com.allocdalloc.colormemorybook.entity.material;

import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookHomeResponse;
import com.allocdalloc.colormemorybook.entity.user.detail.UserAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long>, MaterialRepositoryCustom {
    List<ColorMemoryBookHomeResponse> findByKeyword(UserAccount userAccount, Long cursor, Pageable pageable, String keyword);
}
