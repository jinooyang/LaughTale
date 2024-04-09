package com.jshi.laughtale.member.domain;

import com.jshi.laughtale.security.Role;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Provider provider = Provider.SELF;
    @Column
    private String password;
    @Column(unique = true)
    private String email;
    @Column
    private String nickname;
    @Column
    private Role role;

    public void updatePassword(String password) {
        this.password = password;
    }

    public Member updateNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public void updateRole(Role role) {
        this.role = role;
    }
}