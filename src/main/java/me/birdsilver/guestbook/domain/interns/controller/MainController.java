package me.birdsilver.guestbook.domain.interns.controller;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import me.birdsilver.guestbook.domain.interns.service.MemberService;
import me.birdsilver.guestbook.domain.memo.entity.Memo;
import me.birdsilver.guestbook.domain.memo.service.ArticleService;
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
    private final ArticleService articleService;

    @GetMapping("/")
    public String getAllMembers(Model model) {
        List<Intern> members = memberService.findAll();

        model.addAttribute("members", members);

        return "internList";
    }
    @GetMapping("/members/{id}")
    public String getMember(@PathVariable long id, Model model) {
        Intern intern = memberService.findById(id);

        model.addAttribute("intern", intern);

        return "intern";
    }

    @GetMapping("/new-memo")
    public String newMemo(@RequestParam(required = false) Long id,
                             @RequestParam(required = false) Long internId, Model model) {
        // 메모id가 없는 경우
        if (id == null) {
            model.addAttribute("memo", new Memo());
        // 메모id가 있는 경우
        } else {
            Memo memo = articleService.findById(id);
            model.addAttribute("memo", memo);
        }
        Intern intern = memberService.findById(internId);
        model.addAttribute("intern", intern);
        return "newMemo";
    }


}