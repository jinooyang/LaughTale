package com.jshi.laughtale.security.oauth2;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuth2Attributes {
    KAKAO("kakao", (attributes) -> {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return UserInfo.builder()
                .email((String) kakaoAccount.get("email"))
                .nickname((String) profile.get("nickname"))
                .build();
    });

    private final String registrationId;
    private final Function<Map<String, Object>, UserInfo> of;

    OAuth2Attributes(String registrationId, Function<Map<String, Object>, UserInfo> of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    public static UserInfo extract(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> registrationId.equals(provider.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of.apply(attributes);
    }
}