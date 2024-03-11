package me.birdsilver.guestbook.domain.interns.entity;

import jakarta.persistence.*;
import lombok.*;
import me.birdsilver.guestbook.domain.interns.dto.UpdateInternRequestDto;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Intern {

    // 멤버 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // 멤버 이름
    @Column(name = "name", nullable = false)
    private String name;

    // 소개글
    @Column(name = "introduction", nullable = false)
    private String introduction;

    @Column(name = "photo_url", nullable = true)
    private String photoUrl;

    @Column(name = "avatar_url", nullable = true)
    private String avatarUrl;

    @Column(name = "nick", nullable = true)
    private String nick;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "keyboard", nullable = true)
    private String keyboard;

    @Column(name = "mouse", nullable = true)
    private String mouse;

    public void update(UpdateInternRequestDto requestDto) {
        this.introduction = requestDto.getIntroduction();
//        this.keyboard = requestDto.getKeyboard();
    }
    public void upload(String path) {
        this.keyboard = path;
    }
}
