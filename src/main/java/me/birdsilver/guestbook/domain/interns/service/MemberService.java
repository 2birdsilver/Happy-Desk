package me.birdsilver.guestbook.domain.interns.service;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.entity.Member;
import me.birdsilver.guestbook.domain.interns.dao.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // 모든 멤버 조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // id로 멤버 조회
    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

}
