package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Integer> {
    Optional<ConfirmationToken> findByTokenCode(String token);

    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.isActive = FALSE " +
            "WHERE c.tokenCode = ?1")
    int updateConfirmedAt(String tokenCode);
}
