package me.birdsilver.guestbook.domain.user.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginRequestDto {
    private String email;
    private String password;


}
