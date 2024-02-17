package me.birdsilver.guestbook.domain.interns.dao;

import me.birdsilver.guestbook.domain.interns.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
