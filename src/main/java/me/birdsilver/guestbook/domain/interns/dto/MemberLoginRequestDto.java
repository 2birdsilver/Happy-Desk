package me.birdsilver.guestbook.domain.interns.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginRequestDto {
    private String email;
    private String password;


}
