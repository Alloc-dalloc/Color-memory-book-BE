package com.allocdalloc.colormemorybook.controller.auth;

import com.allocdalloc.colormemorybook.dto.apple.request.AppleLoginRequest;
import com.allocdalloc.colormemorybook.dto.auth.LoginResponseDto;
import com.allocdalloc.colormemorybook.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/apple")
    public ResponseEntity<LoginResponseDto> appleOAuthLogin(@RequestBody AppleLoginRequest appleLoginRequest) {
        return ResponseEntity.ok().body(authService.appleOAuthLogin(appleLoginRequest));
    }

    @PostMapping("/test/generate-token")
    public ResponseEntity<LoginResponseDto> generateToken(@RequestBody Long id) {
        return ResponseEntity.ok().body(authService.issueTestTokenById(id));
    }
}
