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

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "keyboard_img", nullable = true)
    private byte[] keyboardImg;

    @Column(name = "mouse_img", nullable = true)
    private byte[] mouseImg;

    @Column(name = "keyboard_type", nullable = true)
    private String keyboardType;

    @Column(name = "mouse_type", nullable = true)
    private String mouseType;

    public void update(UpdateInternRequestDto requestDto) {
        this.introduction = requestDto.getIntroduction();
    }

    public void uploadImg(String type, byte[] file, String mediaType) {
        if ("keyboard".equals(type)) {
            this.keyboardImg = file;
            this.keyboardType = mediaType;
        }
        if ("mouse".equals(type)) {
            this.mouseImg = file;
            this.mouseType = mediaType;
        }
    }
}
