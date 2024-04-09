package com.jshi.laughtale.security.oauth2;

import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.member.domain.Provider;
import com.jshi.laughtale.security.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class UserInfo {

    private String email;
    private String nickname;
    private Role role = Role.ROLE_USER;
    @Setter
    private Provider provider;

    @Builder
    public UserInfo(String email, String nickname, Provider provider) {
        this.email = email;
        this.nickname = nickname;
        this.provider = provider;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .provider(provider)
                .role(role)
                .build();
    }
}
