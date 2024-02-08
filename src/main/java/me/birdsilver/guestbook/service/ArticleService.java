package me.birdsilver.guestbook.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.birdsilver.guestbook.domain.Article;
import me.birdsilver.guestbook.dto.AddArticleRequest;
import me.birdsilver.guestbook.dto.UpdateArticleRequest;
import me.birdsilver.guestbook.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article save(AddArticleRequest request) {
        return articleRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        articleRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getWriter(), request.getContent(), request.getDate());

        return article;
    }
}
