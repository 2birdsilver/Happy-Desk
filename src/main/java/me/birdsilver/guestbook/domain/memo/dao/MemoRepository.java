package me.birdsilver.guestbook.domain.memo.dao;

import me.birdsilver.guestbook.domain.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByRecipient(Long recipient);

}