package me.birdsilver.guestbook.config.oauth;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.user.dao.MemberRepository;
import me.birdsilver.guestbook.domain.user.entity.Intern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("OAuth2UserCustomService.loadUser메서드 실행!");

        OAuth2User user = super.loadUser(userRequest); // ❶ 요청을 바탕으로 유저 정보를 담은 객체 반환
        System.out.println("user: " + user);
        saveOrUpdate(user);

        return user;
    }

    // ❷ 유저가 있으면 업데이트, 없으면 유저 생성
    private Intern saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        final String name;
        final String email;

        // 카카오로 로그인한 경우
        if (attributes.containsKey("kakao_account")) {
            Map<String, Object> kakao_accout = (Map<String, Object>) attributes.get("kakao_account");

            Map<String, Object> profile = (Map<String, Object>) kakao_accout.get("profile");
            name = (String) profile.get("nickname");
            email = (String) kakao_accout.get("email");
        }


        // 네이버로 로그인한 경우
        else {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");

            name = (String) response.get("name");
//            birthday = (String) response.get("birthday");
//        String birthyear = (String) response.get("birthyear");
//        String mobile = (String) response.get("mobile");
            email = (String) response.get("email");
        }

        Collection<? extends GrantedAuthority> getAuthorities = oAuth2User.getAuthorities();

        Intern user = memberRepository.findByName(name)
                .map(entity -> entity.update(email))
                .orElse(Intern.builder()
                        .name(name)
                        .email(email)
                        .build());

        return memberRepository.save(user);
    }
}

