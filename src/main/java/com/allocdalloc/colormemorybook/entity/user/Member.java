package com.allocdalloc.colormemorybook.entity.user;

import com.allocdalloc.colormemorybook.entity.token.UserToken;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "osogo_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "member_email", nullable = false, unique = true)
    private String email;

    @Column(name = "platform_id", nullable = false, unique = true)
    private String platformId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private UserToken userToken;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public Member(String email, String platformId, Role role) {
        this.email = email;
        this.platformId = platformId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.role = role;
        this.isActive = true;
    }
}