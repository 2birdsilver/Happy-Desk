package me.birdsilver.guestbook.domain.memo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.interns.service.MemberService;
import me.birdsilver.guestbook.domain.memo.dto.UpdateMemoRequest;
import me.birdsilver.guestbook.domain.memo.entity.Memo;
import me.birdsilver.guestbook.domain.memo.dto.AddMemoRequest;
import me.birdsilver.guestbook.domain.memo.dao.MemoRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;
    private final MemberService memberService;

    // 메모 저장
    public Memo save(AddMemoRequest request) {
        return memoRepository.save(request.toEntity());
    }

    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

    public Memo findById(long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public List<Memo> findByRecipient(long recipient) {
        return memoRepository.findByRecipientOrderByDateDesc(recipient);
    }

    // 메모 삭제
    public void delete(long id) {

        memoRepository.deleteById(id);
    }

    // 메모 수정
    @Transactional
    public Memo update(long id, UpdateMemoRequest request) {
        Memo article = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getWriter(), request.getContent(), request.getShape(), request.getColor());

        return article;
    }

    // 게시글을 작성한 유저인지 확인 (추후 리팩토링때 사용할 예정)
    private void authorizeMemoAuthor(Memo memo) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        long userId = memberService.findByEmail(email).getId();
        if (!memo.getAuthenticatedWriter().equals(userId)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
