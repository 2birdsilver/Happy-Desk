package me.birdsilver.guestbook.domain.user.dao;

import me.birdsilver.guestbook.domain.user.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    Optional<User> findByNameAndBirthday(String name, String birthday);

}
