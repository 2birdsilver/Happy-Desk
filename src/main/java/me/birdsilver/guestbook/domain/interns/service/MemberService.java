package me.birdsilver.guestbook.domain.interns.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.dto.MemberLoginResponseDto;
import me.birdsilver.guestbook.domain.interns.dto.UpdateInternRequestDto;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import me.birdsilver.guestbook.domain.interns.dao.MemberRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /** 마이페이지 이미지 저장 */
    @Transactional
    public Intern addImg(Long id, String keyboard, String mouse) {
        Intern intern = findById(id);
        intern.upload(keyboard, mouse);
        return intern;
    }

    /** 이미지 경로 가져오기 */
    public String getImagePath(String type, Long id) {
        Intern intern = findById(id);
        if ("keyboard".equals(type)) {
            return intern.getKeyboard();
        } else if ("mouse".equals(type)) {
            return intern.getMouse();
        } else {
            return "";
        }

    }

}
