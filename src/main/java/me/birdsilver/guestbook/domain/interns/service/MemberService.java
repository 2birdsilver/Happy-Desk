package me.birdsilver.guestbook.domain.interns.service;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import me.birdsilver.guestbook.domain.interns.dao.InternRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final InternRepository memberRepository;

    // 모든 멤버 조회
    public List<Intern> findAll() {
        return memberRepository.findAll();
    }

    // id로 멤버 조회
    public Intern findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

}
