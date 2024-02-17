package me.birdsilver.guestbook.domain.memo.dao;

import me.birdsilver.guestbook.domain.memo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}

