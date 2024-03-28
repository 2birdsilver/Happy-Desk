package me.birdsilver.guestbook.domain.interns.entity;

import jakarta.persistence.*;
import lombok.*;
import me.birdsilver.guestbook.domain.interns.dto.UpdateInternRequestDto;
import me.birdsilver.guestbook.domain.interns.dto.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Intern implements UserDetails {

    // 멤버 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // 멤버 이름
    @Column(name = "name", nullable = false)
    private String name;

    // 소개글
    @Column(name = "introduction", nullable = true)
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

    @Column(name = "mouse", nullable = true)
    private String mouse;

    @Column(name = "birthday", nullable = true)
    private String birthday;

    @Column(name = "birthyear", nullable = true)
    private String birthyear;

    @Column(name = "dept", nullable = true)
    private String dept;

//    @Column(name = "mobile", nullable = true)
//    private String mobile;


    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;


    @Builder
    public Intern(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Intern update(String email) {
        this.email = email;
        return this;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
