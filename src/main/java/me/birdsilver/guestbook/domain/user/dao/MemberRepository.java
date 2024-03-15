package me.birdsilver.guestbook.domain.user.dao;

import me.birdsilver.guestbook.domain.user.entity.Intern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Intern, Long> {

    Optional<Intern> findByEmail(String email);
    Optional<Intern> findByName(String name);
    Optional<Intern> findByNameAndBirthday(String name, String birthday);

}
