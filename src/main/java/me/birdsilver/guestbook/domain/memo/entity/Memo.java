package me.birdsilver.guestbook.domain.memo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Memo {

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
    private Long recipient;

    // 내용
    @Column(name = "content", nullable = false)
    private String content;

    // 작성일
    @JsonFormat(pattern = "yyyy.MM.dd hh:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    // 비밀번호
    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "shape",nullable = false)
    private String shape;

    @Column(name = "color",nullable = false)
    private String color;

    @Column(name = "authenticatedWriter",nullable = true)
    private boolean authenticatedWriter;

    @Builder
    public Memo(String writer, Long recipient, String content, String password, String shape, String color, boolean authenticatedWriter) {
        this.writer = writer;
        this.recipient = recipient;
        this.content = content;
        this.date = LocalDateTime.now();
        this.password = password;
        this.shape = shape;
        this.color = color;
        this.authenticatedWriter = authenticatedWriter;
    }

    public void update(String writer, String content, String shape, String color) {
        this.writer = writer;
        this.content = content;
        this.date = LocalDateTime.now();
        this.shape = shape;
        this.color = color;
    }
}
