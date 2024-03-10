package me.birdsilver.guestbook.config.oauth;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.user.dao.MemberRepository;
import me.birdsilver.guestbook.domain.user.entity.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("OAuth2UserCustomService.loadUser메서드 실행!");

        OAuth2User user = super.loadUser(userRequest); // ❶ 요청을 바탕으로 유저 정보를 담은 객체 반환
        saveOrUpdate(user);
        System.out.println("user: " + user);

        return user;
    }

    // ❷ 유저가 있으면 업데이트, 없으면 유저 생성
    private User saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String name = (String) attributes.get("name");
        String birthday = (String) attributes.get("birthday");
        String birthyear = (String) attributes.get("birthyear");
        String mobile = (String) attributes.get("mobile");

        User intern = memberRepository.findByName(name)
                .orElse(User.builder()
                        .name(name)
                        .birthday(birthday)
                        .birthday(birthyear)
                        .build());

        return memberRepository.save(intern);
    }
}

