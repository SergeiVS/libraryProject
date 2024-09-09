package org.libraryaccountingproject.repositories;

import org.libraryaccountingproject.entities.ConfirmationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationMessageRepository extends JpaRepository<ConfirmationMessage, Integer>  {

    Optional<ConfirmationMessage> findByCodeMessageAndExpiredAtAfter(String codeMessage, LocalDateTime confirmedAt);
}
