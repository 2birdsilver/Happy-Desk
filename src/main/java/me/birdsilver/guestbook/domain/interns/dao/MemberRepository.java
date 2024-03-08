package me.birdsilver.guestbook.domain.interns.dao;

import me.birdsilver.guestbook.domain.interns.entity.Intern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Intern, Long> {

    Optional<Intern> findByEmail(String email);

}
