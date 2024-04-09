package com.jshi.laughtale.security.oauth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class OAuth2UserPrincipal implements OAuth2User {
    private UserInfo userInfo;
    private String name;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;

    public OAuth2UserPrincipal(UserInfo userInfo, String name, Map<String, Object> attributes,
                               Collection<? extends GrantedAuthority> authorities) {
        this.userInfo = userInfo;
        this.name = name;
        this.attributes = attributes;
        this.authorities = authorities;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return name;
    }

}
