package com.jshi.laughtale.security;

public enum Role {

    ROLE_USER("USER"),
    ROLE_ANONYMOUS("ANONYMOUS"),
    ROLE_ADMIN("ADMIN");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}