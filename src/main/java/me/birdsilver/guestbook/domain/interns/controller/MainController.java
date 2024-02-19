package me.birdsilver.guestbook.domain.interns.controller;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import me.birdsilver.guestbook.domain.interns.service.MemberService;
import me.birdsilver.guestbook.domain.memo.entity.Memo;
import me.birdsilver.guestbook.domain.memo.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final MemberService memberService;
    private final MemoService memoService;

    @GetMapping("/")
    public String getAllMembers(Model model) {
        List<Intern> members = memberService.findAll();

        model.addAttribute("members", members);

        return "internList";
    }
    @GetMapping("/members/{id}")
    public String getMember(@PathVariable long id, Model model) {
        Intern member = memberService.findById(id);

        model.addAttribute("member", member);

        return "intern";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new Memo());

            Intern intern = memberService.findById(id);
            model.addAttribute("intern", intern);
        } else {
            Memo article = memoService.findById(id);
            model.addAttribute("article", article);
        }

        return "newMemo";
    }


}