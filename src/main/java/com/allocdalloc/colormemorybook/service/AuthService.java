package com.allocdalloc.colormemorybook.service;

import com.allocdalloc.colormemorybook.config.apple.AppleOAuthUserProvider;
import com.allocdalloc.colormemorybook.config.jwt.JwtTokenUtil;
import com.allocdalloc.colormemorybook.dto.apple.request.AppleLoginRequest;
import com.allocdalloc.colormemorybook.dto.apple.response.ApplePlatformMemberResponse;
import com.allocdalloc.colormemorybook.dto.auth.LoginResponseDto;
import com.allocdalloc.colormemorybook.entity.user.Member;
import com.allocdalloc.colormemorybook.entity.user.MemberRepository;
import com.allocdalloc.colormemorybook.entity.user.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final AppleOAuthUserProvider appleOAuthUserProvider;

    public LoginResponseDto appleOAuthLogin(AppleLoginRequest request) {
        ApplePlatformMemberResponse applePlatformMember =
                appleOAuthUserProvider.getApplePlatformMember(request.getToken());
        log.info("applePlatformMember platformId: {}", applePlatformMember.getPlatformId());
        log.info("applePlatformMember email: {}", applePlatformMember.getEmail());
        String platformId = applePlatformMember.getPlatformId();

        return memberRepository.findByEmail(applePlatformMember.getEmail())
                .map(this::issueToken)
                .orElseGet(() -> {
                    Member savedMember = memberRepository.save(Member.builder()
                            .email(applePlatformMember.getEmail())
                            .platformId(platformId)
                            .role(Role.ROLE_USER).build());

                    return issueToken(savedMember);
                }); // 오 여기 코드 잘 짰네 깔끔하게
    }

    private LoginResponseDto issueToken(final Member findMember) {
        String accessToken = jwtTokenUtil.generateAccessToken(findMember.getId(), findMember.getRole(), findMember.getEmail(), true);
        String refreshToken = jwtTokenUtil.generateRefreshToken(findMember.getId(), findMember.getRole(), findMember.getEmail(), true);

        return LoginResponseDto.of(accessToken, refreshToken);
    }

    public LoginResponseDto issueTestTokenById(Long id){
        Member findMember = memberRepository.findById(id).orElseThrow();

        return issueToken(findMember);
    }
}
