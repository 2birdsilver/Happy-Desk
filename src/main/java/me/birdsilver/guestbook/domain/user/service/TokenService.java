package me.birdsilver.guestbook.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.config.jwt.TokenProvider;
import me.birdsilver.guestbook.domain.user.entity.Intern;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

//        String value = refreshTokenService.findByRefreshToken(refreshToken).getValue();
//        Intern intern = memberService.findById(userId);

          Intern intern = new Intern();
        return tokenProvider.generateToken(intern, Duration.ofHours(2));
    }
}

