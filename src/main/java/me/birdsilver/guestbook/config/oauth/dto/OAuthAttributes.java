package me.birdsilver.guestbook.config.oauth.dto;

import lombok.Builder;
import lombok.Getter;
import me.birdsilver.guestbook.domain.user.dto.Role;
import me.birdsilver.guestbook.domain.user.entity.User;

import java.util.Date;
import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String birthday;
    private String birthyear;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String birthday, String birthyear) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.birthday = birthday;
        this.birthyear = birthyear;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .birthday((String) response.get("birthday"))
                .birthyear((String) response.get("birthyear"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    /*public  User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .role(Role.GUEST)
                .build();
    }*/
}
