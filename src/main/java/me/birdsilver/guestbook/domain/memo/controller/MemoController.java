package me.birdsilver.guestbook.domain.memo.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.memo.dto.AddMemoRequest;
import me.birdsilver.guestbook.domain.memo.dto.DeleteMemoRequest;
import me.birdsilver.guestbook.domain.memo.dto.UpdateMemoRequest;
import me.birdsilver.guestbook.domain.memo.entity.Memo;
import me.birdsilver.guestbook.domain.memo.service.MemoService;
import me.birdsilver.guestbook.domain.interns.entity.Intern;
import me.birdsilver.guestbook.domain.interns.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    private final MemberService memberService;


    // 메모 작성
    @PostMapping
    public ResponseEntity<Memo> addMemo(@RequestBody AddMemoRequest request, @Nullable Principal principal) {
        if (request.getContent() == null || request.getContent().isEmpty()) {
            request.setContent(" ");
        }

        // 로그인한 작성자의 경우 => AuthenticatedWriter필드에 userId 저장
        if (principal != null) {
            String email = principal.getName();

            Intern user = memberService.findByEmail(email);

            request.setAuthenticatedWriter(user.getId());
            request.setWriter(user.getName());


        }
        Memo savedMemo = memoService.save(request);
        return new ResponseEntity<>(savedMemo, HttpStatus.CREATED);
    }


//    @GetMapping
//    public List<Memo> getAllMemos() {
//        return memoService.findAll();
//    }


    // 유저별 메모리스트 조회 페이지
    @GetMapping("/{recipientId}")
    public ResponseEntity<List<Memo>> getMemosByRecipient(@PathVariable Long recipientId) {

        List<Memo> memos = memoService.findByRecipient(recipientId);
        if (memos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(memos);
    }


    // 메모 수정 페이지
    @GetMapping("/update/{id}")
    public ResponseEntity<Memo> getMemoById(@PathVariable long id) {
        Memo memo = memoService.findById(id);
        return ResponseEntity.ok(memo);
    }


    // 메모 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMemo(@PathVariable long id, @RequestBody UpdateMemoRequest request, @Nullable Principal principal) {
        Memo memo = memoService.findById(id);

        // 로그인한 작성자의 메모의 경우 => 본인 확인
        if (principal != null) {
            String email = principal.getName();
            Long userId = memberService.findByEmail(email).getId();

            if (request.getAuthenticatedWriter() != userId) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized");
            }
        }

        // 비로그인한 작성자의 메모 => 비밀번호 확인
        if (!request.getPassword().equals(memo.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password mismatch");
        }

        Memo updatedMemo = memoService.update(id, request);
        return ResponseEntity.ok(updatedMemo);
    }


    // 메모 삭제
    @PostMapping("/delete")
    public ResponseEntity<?> deleteMemo(@RequestBody DeleteMemoRequest request, @Nullable Principal principal) {
        Long memoId = request.getMemoId();
        Memo memo = memoService.findById(memoId);

        // 로그인한 작성자의 메모의 경우 => 본인 확인
        if (principal != null) {
            String email = principal.getName();
            Long userId = memberService.findByEmail(email).getId();

            if (memo.getAuthenticatedWriter() != userId) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized");
            }
        }

        // 비로그인한 작성자의 메모 => 비밀번호 확인
        else if (!memo.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password mismatch");
        }

        memoService.delete(memoId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}