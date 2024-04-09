package com.jshi.laughtale.member.mapper;

import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.member.domain.Provider;
import com.jshi.laughtale.member.dto.MemberCheck;
import com.jshi.laughtale.member.dto.MemberLogin;
import com.jshi.laughtale.member.dto.MemberSignup;
import com.jshi.laughtale.security.Role;

public class MemberMapper {
    public static Member signupToMember(MemberSignup.Request signup) {
        return Member.builder()
                .email(signup.getEmail())
                .password(signup.getPassword())
                .role(Role.ROLE_USER)
                .provider(Provider.SELF)
                .nickname(signup.getNickname())
                .build();
    }

    public static MemberCheck.Response toCheckResponse(Member member) {
        return MemberCheck.Response.builder()
                .role(member.getRole().value())
                .build();
    }

    public static MemberLogin.Response toLoginResponse(String token) {
        return MemberLogin.Response.builder()
                .accessToken(token)
                .build();
    }
}
