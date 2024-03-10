package me.birdsilver.guestbook.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.user.dao.MemberRepository;
import me.birdsilver.guestbook.domain.user.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public User loadUserByUsername(String name) {
        return memberRepository.findByName(name)
                 .orElseThrow(() -> new IllegalArgumentException((name)));
    }
}
