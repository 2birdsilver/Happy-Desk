package me.birdsilver.guestbook.domain.memo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.memo.dto.UpdateMemoRequest;
import me.birdsilver.guestbook.domain.memo.entity.Memo;
import me.birdsilver.guestbook.domain.memo.dto.AddMemoRequest;
import me.birdsilver.guestbook.domain.memo.dao.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

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

    public void delete(long id) {
        memoRepository.deleteById(id);
    }

    @Transactional
    public Memo update(long id, UpdateMemoRequest request) {
        Memo article = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getWriter(), request.getContent(), request.getShape(), request.getColor());

        return article;
    }
}
