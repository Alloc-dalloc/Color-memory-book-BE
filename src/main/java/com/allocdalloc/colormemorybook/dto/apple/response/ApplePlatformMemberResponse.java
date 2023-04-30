package com.allocdalloc.colormemorybook.dto.apple.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ApplePlatformMemberResponse {

    private String platformId;
    private String email;
}