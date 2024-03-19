package me.birdsilver.guestbook.domain.interns.service;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.dao.RefreshTokenRepository;
import me.birdsilver.guestbook.domain.interns.entity.RefreshToken;
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

