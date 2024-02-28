package me.birdsilver.guestbook.domain.memo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.birdsilver.guestbook.domain.memo.entity.Memo;
import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddMemoRequest {
    private String writer;
    private Long recipient;
    private String content;
    private String password;
    private  String shape;
    private String color;

    public Memo toEntity() {
        return Memo.builder()
                .writer(writer)
                .recipient(recipient)
                .content(content)
                .password(password)
                .shape(shape)
                .color(color)
                .build();
    }
}
