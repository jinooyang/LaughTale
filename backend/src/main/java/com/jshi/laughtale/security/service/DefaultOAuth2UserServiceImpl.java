package com.jshi.laughtale.security.service;

import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.member.domain.Provider;
import com.jshi.laughtale.member.repository.MemberRepository;
import com.jshi.laughtale.security.oauth2.OAuth2Attributes;
import com.jshi.laughtale.security.oauth2.OAuth2UserPrincipal;
import com.jshi.laughtale.security.oauth2.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultOAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String nameAttributeKey = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        UserInfo userInfo = OAuth2Attributes.extract(registrationId, attributes);
        userInfo.setProvider(Provider.valueOf(registrationId.toUpperCase()));

        Member member = saveOrUpdate(userInfo);

        return new OAuth2UserPrincipal(
                userInfo,
                nameAttributeKey,
                attributes,
                List.of(new SimpleGrantedAuthority(member.getRole().value()))
        );
    }

    private Member saveOrUpdate(UserInfo userInfo) {
        Member member = memberRepository.findByEmailAndProvider(userInfo.getEmail(), userInfo.getProvider())
                .map(entity -> entity.updateNickname(userInfo.getNickname()))
                .orElse(userInfo.toEntity());

        return memberRepository.save(member);
    }
}