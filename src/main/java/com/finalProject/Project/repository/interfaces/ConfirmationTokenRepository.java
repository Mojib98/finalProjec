package com.finalProject.Project.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenRepository,Integer> {
    Optional<ConfirmationTokenRepository> findByToken(String token);

 /*   @Transactional
    @Modifying
    @Query("UPDATE Token c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);*/
}
