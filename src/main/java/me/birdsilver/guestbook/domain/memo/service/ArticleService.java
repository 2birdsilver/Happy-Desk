package me.birdsilver.guestbook.domain.memo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.memo.entity.Memo;
import me.birdsilver.guestbook.domain.memo.dto.AddArticleRequest;
import me.birdsilver.guestbook.domain.memo.dto.UpdateArticleRequest;
import me.birdsilver.guestbook.domain.memo.dao.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final MemoRepository articleRepository;

    public Memo save(AddArticleRequest request) {
        return articleRepository.save(request.toEntity());
    }

    public List<Memo> findAll() {
        return articleRepository.findAll();
    }

    public Memo findById(long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        articleRepository.deleteById(id);
    }

    @Transactional
    public Memo update(long id, UpdateArticleRequest request) {
        Memo article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getWriter(), request.getContent(), request.getDate());

        return article;
    }
}
