package me.birdsilver.guestbook.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.user.dao.RefreshTokenRepository;
import me.birdsilver.guestbook.domain.user.entity.RefreshToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}

