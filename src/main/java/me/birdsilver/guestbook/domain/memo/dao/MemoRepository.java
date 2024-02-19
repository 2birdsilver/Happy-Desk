package me.birdsilver.guestbook.domain.memo.dao;

import me.birdsilver.guestbook.domain.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}

