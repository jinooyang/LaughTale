package com.jshi.laughtale.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MemberSignup {

    @Getter
    @Builder
    public static class Request {
        private String email;
        @Setter
        private String password;
        private String nickname;
    }
}
