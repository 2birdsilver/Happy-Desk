package me.birdsilver.guestbook.domain.interns.dao;

import me.birdsilver.guestbook.domain.interns.entity.Intern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternRepository extends JpaRepository<Intern, Long> {
}
