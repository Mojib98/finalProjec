package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
//    Optional<User> findByEmail(String username);
    Optional<User> findByEmail(String eamil);
    List<User> findAllByTimeBetween(LocalDateTime start, LocalDateTime end);

   /* @Transactional
    @Modifying*/
  /*  @Query("UPDATE User a " +
            "SET a.status = where a.email = ?1")
    int enableAppUser(String email);*/
}
