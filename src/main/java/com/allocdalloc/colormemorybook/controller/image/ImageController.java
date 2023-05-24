package com.allocdalloc.colormemorybook.controller.image;

import com.allocdalloc.colormemorybook.dto.image.response.ImageTotalAnalysisResponseDto;
import com.allocdalloc.colormemorybook.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/analysis")
    public ResponseEntity<ImageTotalAnalysisResponseDto> uploadImage(
            @RequestPart(value = "image") MultipartFile multipartFile
            ) throws Exception {

        return ResponseEntity.ok().body(imageService.imageAnalysis(multipartFile));
    }
}
