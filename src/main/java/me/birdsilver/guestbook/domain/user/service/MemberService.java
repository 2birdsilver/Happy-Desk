package me.birdsilver.guestbook.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.user.dto.MemberLoginResponseDto;
import me.birdsilver.guestbook.domain.user.entity.Intern;
import me.birdsilver.guestbook.domain.user.dao.MemberRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    // 이름으로 멤버 조회
    public Intern findByName(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + name));
    }


    // email로 멤버 조회
    public Intern findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    // 이름과 생일로 조회
    public Intern findByNameAndBirthday(String name, String birthday) {
        return memberRepository.findByNameAndBirthday(name, birthday)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + name + " and " + birthday));
    }

    // 단순 로그인
    public MemberLoginResponseDto login(String email, String password) {
        Optional<MemberLoginResponseDto> optionMember = null;
        Intern intern = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        if (intern.getPassword().equals(password)) {
            optionMember = memberRepository.findByEmail(email).map(MemberLoginResponseDto::of);
        } else {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        if (optionMember.isEmpty())
            return null;
        return optionMember.get();
    }

    // 유저확인
    private static void authorizeArticleAuthor(Intern user) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!user.getEmail().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }

}