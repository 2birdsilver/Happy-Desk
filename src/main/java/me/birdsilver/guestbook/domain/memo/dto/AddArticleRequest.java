package me.birdsilver.guestbook.domain.memo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.birdsilver.guestbook.domain.memo.entity.Article;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String writer;
    private String recipient;
    private String content;
    private Date date;

    public Article toEntity() {
        return Article.builder()
                .writer(writer)
                .recipient(recipient)
                .content(content)
                .date(date)
                .build();
    }
}
