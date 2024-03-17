package me.birdsilver.guestbook.domain.memo.controller;

import me.birdsilver.guestbook.domain.memo.dto.AddMemoRequest;
import me.birdsilver.guestbook.domain.memo.dto.DeleteMemoRequest;
import me.birdsilver.guestbook.domain.memo.dto.UpdateMemoRequest;
import me.birdsilver.guestbook.domain.memo.entity.Memo;
import me.birdsilver.guestbook.domain.memo.service.MemoService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/memo")
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    public ResponseEntity<Memo> addMemo(@RequestBody AddMemoRequest request) {
        if (request.getContent() == null || request.getContent().isEmpty()) {
            request.setContent(" ");
        }
        Memo savedMemo = memoService.save(request);
        return new ResponseEntity<>(savedMemo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Memo> getAllMemos() {
        return memoService.findAll();
    }

    
    @GetMapping("/update/{id}")
    public ResponseEntity<Memo> getMemoById(@PathVariable long id) {
        Memo memo = memoService.findById(id);

        return ResponseEntity.ok(memo);
    }

    // 인턴별 메모 조회
    @GetMapping("/{recipientId}")
    public ResponseEntity<List<Memo>> getMemosByRecipient(@PathVariable Long recipientId) {

        List<Memo> memos = memoService.findByRecipient(recipientId);
        if (memos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(memos);
    }

    
    // 메모 삭제
    @PostMapping("/delete")
    public ResponseEntity<?> deleteMemo(@RequestBody DeleteMemoRequest request) {
        Long memoId = request.getMemoId();
        Memo memo = memoService.findById(memoId);

        // 실명으로 작성하였거나, 비밀번호가 일치하는 경우 삭제 처리
        boolean isConditionMatched = Optional.ofNullable(request.isAuthenticatedWriter())
                .orElse(false)
                || Optional.ofNullable(memo.getPassword())
                .map(password -> password.equals(request.getPassword()))
                .orElse(false);

        if (isConditionMatched) {
            memoService.delete(memoId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("mismatched");
        }

    }

    // 메모 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMemo(@PathVariable long id, @RequestBody UpdateMemoRequest request) {
        Memo memo = memoService.findById(id);

        // 실명으로 작성하였거나, 비밀번호가 일치하는 경우 수정 처리
        boolean isConditionMatched = Optional.ofNullable(request.isAuthenticatedWriter())
                .orElse(false)
                || Optional.ofNullable(memo.getPassword())
                .map(password -> password.equals(request.getPassword()))
                .orElse(false);

        if (isConditionMatched) {
            Memo updatedMemo = memoService.update(id, request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("mismatched");
        }
    }
}
