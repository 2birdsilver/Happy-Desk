package me.birdsilver.guestbook.repository;

import me.birdsilver.guestbook.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
