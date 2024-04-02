package com.jshi.laughtale.member.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberCheck {

    @Builder
    @Getter
    public static class Response {
        String role;
    }
}
