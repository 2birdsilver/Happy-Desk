package me.birdsilver.guestbook.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // 작성자
    @Column(name = "writer", nullable = false)
    private String writer;

    // 받는자
    @Column(name = "recipient", nullable = false)
    private String recipient;

    // 내용
    @Column(name = "content", nullable = false)
    private String content;

    // 작성일
    @Column(name = "date", nullable = false)
    private Date date;

    // 비밀번호
    @Column(name = "password", nullable = false)
    private String password;

    @Builder
    public Article(String writer, String recipient, String content, Date date, String password) {
        this.writer = writer;
        this.recipient = recipient;
        this.content = content;
        this.date = date;
        this.password = password;
    }

    public void update(String writer, String content, Date date) {
        this.writer = writer;
        this.content = content;
        this.date = date;
    }
}
