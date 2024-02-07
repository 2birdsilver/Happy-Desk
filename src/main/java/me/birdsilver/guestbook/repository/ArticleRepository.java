package me.birdsilver.guestbook.repository;

import me.birdsilver.guestbook.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}

