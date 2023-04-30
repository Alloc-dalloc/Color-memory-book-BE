package com.allocdalloc.colormemorybook.entity.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findByMember_Id(Long memberId);
    Optional<UserToken> findUserTokenByRefreshToken(String token);
}
