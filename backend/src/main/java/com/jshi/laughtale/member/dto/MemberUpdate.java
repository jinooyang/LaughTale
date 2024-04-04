package com.jshi.laughtale.member.dto;

import com.jshi.laughtale.security.Role;
import lombok.Builder;
import lombok.Getter;

public class MemberUpdate {

    @Getter
    @Builder
    public static class Request {
        private String password;
        private String nickname;
    }

    @Getter
    @Builder
    public static class RoleRequest {
        private String email;
        private Role role;
    }
}
