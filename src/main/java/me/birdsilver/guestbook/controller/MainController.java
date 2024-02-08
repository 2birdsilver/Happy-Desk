package me.birdsilver.guestbook.controller;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.Member;
import me.birdsilver.guestbook.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final MemberService memberService;

    @GetMapping("/members")
    public String getAllMembers(Model model) {
        List<Member> members = memberService.findAll();

        model.addAttribute("members", members);

        return "memberList";
    }
    @GetMapping("/members/{id}")
    public String getMember(@PathVariable long id, Model model) {
        Member member = memberService.findById(id);

        model.addAttribute("member", member);

        return "member";
    }


}