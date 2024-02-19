package me.birdsilver.guestbook.domain.memo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.birdsilver.guestbook.domain.memo.entity.Memo;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddMemoRequest {
    private String writer;
    private String recipient;
    private String content;
    private Date date;
    private String password;

    public Memo toEntity() {
        return Memo.builder()
                .writer(writer)
                .recipient(recipient)
                .content(content)
                .date(date)
                .password(password)
                .build();
    }
}
