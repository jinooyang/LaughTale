package com.jshi.laughtale.security;

public enum Role {

    ROLE_USER("USER"),
    ROLE_TEMPORARY_USER("TEMPORARY_USER"),
    ROLE_ADMIN("ADMIN");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }

    @Override
    public String toString() {
        return name();
    }
}