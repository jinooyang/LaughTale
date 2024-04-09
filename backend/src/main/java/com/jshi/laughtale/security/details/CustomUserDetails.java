package com.jshi.laughtale.security.details;

import com.jshi.laughtale.member.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;


    public CustomUserDetails(Long id, String email, String password, String nickname,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.authorities = authorities;
    }

    public static CustomUserDetails withMember(Member member) {
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(member.getRole().toString());
        return new CustomUserDetails(
                member.getId(),
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                Collections.unmodifiableList(authorityList)
        );
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    /**
     * 사용자 계정이 유효한가?
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 사용자 계정이 lock인가?
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호가 유효한가?
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 사용자가 활성화 되었는가?
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
