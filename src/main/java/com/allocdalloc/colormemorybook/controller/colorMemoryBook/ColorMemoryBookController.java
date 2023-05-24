package com.allocdalloc.colormemorybook.controller.colorMemoryBook;

import com.allocdalloc.colormemorybook.dto.colorMemoryBook.request.ColorMemoryBookRegisterRequestDto;
import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookDetailInfoResponseDto;
import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookHomeResponse;
import com.allocdalloc.colormemorybook.dto.colorMemoryBook.response.ColorMemoryBookRegisterResponseDto;
import com.allocdalloc.colormemorybook.entity.user.detail.UserAccount;
import com.allocdalloc.colormemorybook.service.ColorMemoryBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/color-memory-book")
@RequiredArgsConstructor
public class ColorMemoryBookController {
    private final ColorMemoryBookService colorMemoryBookService;

    @PostMapping("/register")
    public ResponseEntity<ColorMemoryBookRegisterResponseDto> registerColorMemoryBook
            (
                    @AuthenticationPrincipal UserAccount userAccount,
                    @RequestBody ColorMemoryBookRegisterRequestDto colorMemoryBookRegisterRequestDto
            )
    {
        ColorMemoryBookRegisterResponseDto colorMemoryBookRegisterResponseDto = colorMemoryBookService.registerColorMemoryBook(userAccount, colorMemoryBookRegisterRequestDto);
        return ResponseEntity.ok().body(colorMemoryBookRegisterResponseDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ColorMemoryBookHomeResponse>> home
            (
                    @AuthenticationPrincipal UserAccount userAccount,
                    @Nullable @RequestParam Long cursor,
                    @PageableDefault(size = 6) Pageable pageable,
                    @RequestParam(required = false, defaultValue = "") String keyword
            )
    {
        List<ColorMemoryBookHomeResponse> materialAtHome = colorMemoryBookService.getMaterialAtHome(userAccount, cursor, pageable, keyword);
        return ResponseEntity.ok().body(materialAtHome);
    }

    @GetMapping("/detail/{materialId}")
    public ResponseEntity<ColorMemoryBookDetailInfoResponseDto> getMaterialDetailInfo
            (
                    @AuthenticationPrincipal UserAccount userAccount,
                    @NotNull @PathVariable Long materialId
            )
    {
        ColorMemoryBookDetailInfoResponseDto materialDetailInfo = colorMemoryBookService.getMaterialDetailInfo(userAccount, materialId);
        return ResponseEntity.ok().body(materialDetailInfo);
    }
}
