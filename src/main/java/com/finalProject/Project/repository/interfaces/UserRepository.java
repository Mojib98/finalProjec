package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String eamil);
   /* @Transactional
    @Modifying*/
  /*  @Query("UPDATE User a " +
            "SET a.status = where a.email = ?1")
    int enableAppUser(String email);*/
}
