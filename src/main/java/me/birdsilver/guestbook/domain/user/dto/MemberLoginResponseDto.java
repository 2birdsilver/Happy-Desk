package me.birdsilver.guestbook.domain.user.dto;

import lombok.*;
import me.birdsilver.guestbook.domain.user.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginResponseDto {

    private Long id;
    private String name;
    private String avatarUrl;

    public static MemberLoginResponseDto of(User intern) {
        return MemberLoginResponseDto.builder()
                .id(intern.getId())
                .name(intern.getName())
                .avatarUrl(intern.getAvatarUrl())
                .build();
    }
}
