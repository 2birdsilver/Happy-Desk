package me.birdsilver.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.birdsilver.guestbook.domain.Article;
import me.shinsunyoung.springbootdeveloper.domain.Article;

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
