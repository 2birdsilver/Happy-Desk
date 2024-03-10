package me.birdsilver.guestbook.domain.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserViewController {

    @GetMapping("/login")
    public String login() {
        return "naverLogin";
    }

    @GetMapping("/naverLoginSuccess")
    public String naverLoginSuccess(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("user", principal.getAttributes());
        return "naverLoginSuccess";
    }

    @GetMapping("/error")
    @ResponseBody
    public String error() {
        return "뭔가 잘못됐어";
    }

    @GetMapping("/login/oauth2/code/naver")
    public String callback() {
        return "naverCallback";
    }

}
