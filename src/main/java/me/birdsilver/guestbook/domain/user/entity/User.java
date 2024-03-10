package me.birdsilver.guestbook.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import me.birdsilver.guestbook.domain.user.dto.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

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

    @Column(name = "birthday", nullable = true)
    private String birthday;

    @Column(name = "birthyear", nullable = true)
    private String birthyear;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Builder
    public User(String name, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User update(String nick) {
        this.nick = nick;

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
