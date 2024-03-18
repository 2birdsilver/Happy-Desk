package me.birdsilver.guestbook.domain.interns.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.dto.UpdateInternRequestDto;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import me.birdsilver.guestbook.domain.interns.dao.MemberRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /** 모든 멤버 조회 */
    public List<Intern> findAll() {
        return memberRepository.findAll();
    }

    /** id로 멤버 조회 */
    public Intern findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }


    /** 단순 로그인 */
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

    /** 마이페이지 비밀번호 확인 */
    public boolean checkMember(Long id, String password) {
        Intern intern = findById(id);

        if (intern.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    /** 마이페이지 정보 수정 */
    @Transactional
    public Intern updateUserInfo(UpdateInternRequestDto request) {
        Intern intern = findById(request.getId());
        intern.update(request);
        return intern;
    }



 // 유저확인
    private static void authorizeArticleAuthor(Intern user) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!user.getEmail().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}

