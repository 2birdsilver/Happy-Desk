package me.birdsilver.guestbook.domain.memo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeleteMemoRequest {
    private Long memoId;
    private String password;
    private long authenticatedWriter;
}
