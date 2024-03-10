package me.birdsilver.guestbook.domain.user.controller;

import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.user.entity.User;
import me.birdsilver.guestbook.domain.user.service.MemberService;
import me.birdsilver.guestbook.domain.memo.service.MemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/") // API 경로에
public class MainController {
    private final MemberService memberService;
    private final MemoService memoService;

    @GetMapping("/members")
    public ResponseEntity<List<User>> getAllMembers() {
        List<User> members = memberService.findAll();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<User> getMember(@PathVariable long id) {
        User member = memberService.findById(id);
        return member != null ? ResponseEntity.ok(member) : ResponseEntity.notFound().build();
    }
//글등록 누르면 여기로 이동
//    @GetMapping("/new-article")
//    public String newArticle(@RequestParam(required = false) Long id, Model model) {
//        if (id == null) {
//            model.addAttribute("memo", new Memo());
//
//            Intern intern = memberService.findById(id);
//            model.addAttribute("intern", intern);
//        } else {
//            Memo memo = memoService.findById(id);
//            model.addAttribute("memo", memo);
//        }
//
//
//
//        return "newMemo";
//    }

    /*react연동 test*/
    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }

}