package me.birdsilver.guestbook.domain.memo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateMemoRequest {
    private String writer;
    private String content;
    private Date date;
    private String password;
    private String color;
    private String shape;
    private boolean authenticatedWriter;
}
