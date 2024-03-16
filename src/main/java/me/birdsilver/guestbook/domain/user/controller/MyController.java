package me.birdsilver.guestbook.domain.user.controller;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.user.dto.MemberLoginRequestDto;
import me.birdsilver.guestbook.domain.user.dto.MemberLoginResponseDto;
import me.birdsilver.guestbook.domain.user.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class MyController {

    private final MemberService memberService;

    /**
     * 로그인
     * @param requestDto
     * @return 응답
     */
//    @PostMapping("/login")
//    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) {
//        MemberLoginResponseDto responseDto = memberService.login(requestDto.getEmail(), requestDto.getPassword());
//
//        return  ResponseEntity.ok(responseDto);
//    }



}
