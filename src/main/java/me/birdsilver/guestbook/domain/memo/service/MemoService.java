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

    private final MemoRepository MemoRepository;

    public Memo save(AddMemoRequest request) {
        return MemoRepository.save(request.toEntity());
    }

    public List<Memo> findAll() {
        return MemoRepository.findAll();
    }

    public Memo findById(long id) {
        return MemoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        MemoRepository.deleteById(id);
    }

    @Transactional
    public Memo update(long id, UpdateMemoRequest request) {
        Memo article = MemoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getWriter(), request.getContent(), request.getDate());

        return article;
    }
}
