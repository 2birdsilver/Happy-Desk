package me.birdsilver.guestbook.controller;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.entity.Member;
import me.birdsilver.guestbook.domain.interns.service.MemberService;
import me.birdsilver.guestbook.domain.memo.entity.Article;
import me.birdsilver.guestbook.domain.memo.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final MemberService memberService;
    private final ArticleService articleService;

    @GetMapping("/")
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

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new Article());
        } else {
            Article article = articleService.findById(id);
            model.addAttribute("article", article);
        }

        return "newArticle";
    }


}