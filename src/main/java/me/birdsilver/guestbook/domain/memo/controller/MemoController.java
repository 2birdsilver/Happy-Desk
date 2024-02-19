package me.birdsilver.guestbook.domain.memo.controller;

import me.birdsilver.guestbook.domain.memo.dto.AddMemoRequest;
import me.birdsilver.guestbook.domain.memo.dto.UpdateMemoRequest;
import me.birdsilver.guestbook.domain.memo.entity.Memo;
import me.birdsilver.guestbook.domain.memo.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memo")
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    public ResponseEntity<Memo> addMemo(@RequestBody AddMemoRequest request) {
        Memo savedMemo = memoService.save(request);
        return new ResponseEntity<>(savedMemo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Memo> getAllMemos() {
        return memoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Memo> getMemoById(@PathVariable long id) {
        Memo memo = memoService.findById(id);
        return ResponseEntity.ok(memo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable long id) {
        memoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Memo> updateMemo(@PathVariable long id, @RequestBody UpdateMemoRequest request) {
        Memo updatedMemo = memoService.update(id, request);
        return ResponseEntity.ok(updatedMemo);
    }
}
