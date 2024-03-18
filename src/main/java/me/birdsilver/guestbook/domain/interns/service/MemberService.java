package me.birdsilver.guestbook.domain.interns.service;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.dto.MemberLoginRequestDto;
import me.birdsilver.guestbook.domain.interns.dto.MemberLoginResponseDto;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import me.birdsilver.guestbook.domain.interns.dao.MemberRepository;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // 모든 멤버 조회
    public List<Intern> findAll() {
        return memberRepository.findAll();
    }

    // id로 멤버 조회
    public Intern findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    // 단순 로그인
    public MemberLoginResponseDto login(String email, String password) {
        Optional<MemberLoginResponseDto> optionMember = null;
        Intern intern = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (intern.getPassword().equals(password)) {
            optionMember = memberRepository.findByEmail(email).map(MemberLoginResponseDto::of);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (optionMember.isEmpty())
            return null;
        return optionMember.get();
    }

}
