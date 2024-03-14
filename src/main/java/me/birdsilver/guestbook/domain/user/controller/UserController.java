package me.birdsilver.guestbook.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/api/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("로그아웃 완료!");
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @GetMapping("/api/user")
    public ResponseEntity<String> user(@AuthenticationPrincipal Principal principal) {
        System.out.println("principal: " + principal);
        return ResponseEntity.status(HttpStatus.OK)
                .body("user");
    }

}
