package com.allocdalloc.colormemorybook.entity.token;

import com.allocdalloc.colormemorybook.entity.user.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MEMBER_TOKEN")
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_token_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "refresh_token",length = 500, nullable = false)
    private String refreshToken;

    @Builder
    public UserToken(Member member, String refreshToken) {
        this.member = member;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
