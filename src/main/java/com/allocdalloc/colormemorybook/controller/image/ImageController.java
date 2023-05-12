package com.allocdalloc.colormemorybook.controller.image;

import com.allocdalloc.colormemorybook.dto.image.response.ImageUploadResponseDto;
import com.allocdalloc.colormemorybook.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageUploadResponseDto> uploadImage(
            @RequestParam(value = "filename") String fileName,
            @RequestPart(value = "image") MultipartFile multipartFile
            ) throws IOException {

        return ResponseEntity.ok(imageService.upload(multipartFile, fileName));
    }
}
