package me.birdsilver.guestbook.domain.user.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.user.entity.Intern;
import me.birdsilver.guestbook.domain.user.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final MemberService memberService;

    @GetMapping("/api/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("로그아웃 완료!");
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
    }

//    @GetMapping("/authenticated/api/userInfo")
//    public ResponseEntity<User> getUserInfo2(@Nullable  Principal principal) {
//
//                String email = principal.getName();
//                User user = memberService.findByEmail(email);
//
//                return ResponseEntity.status(HttpStatus.OK)
//                        .body(user);
//
//    }

//    @PostMapping("/api/authenticated/userInfo")
    private ResponseEntity<Intern> authorizeArticleAuthor2() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Intern user = memberService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
        }

    @PostMapping("/api/authenticated/userInfo")
    public ResponseEntity<Intern> getUserInfo(@Nullable Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            Intern user = memberService.findByEmail(email);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
    }

}
