package org.libraryaccountingproject.services;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.entities.ConfirmationMessage;
import org.libraryaccountingproject.entities.User;
import org.libraryaccountingproject.repositories.ConfirmationMessageRepository;
import org.libraryaccountingproject.exeptions.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationMessageServices {

    private final ConfirmationMessageRepository confirmationRepository;

    public String createNewMessage(User user) {

        String message = UUID.randomUUID().toString();

        ConfirmationMessage confirmationMessage = ConfirmationMessage.builder()
                .user(user)
                .codeMessage(message)
                .expiredAt(LocalDateTime.now().plusHours(1))
                .build();
        confirmationRepository.save(confirmationMessage);

        return message;
    }

    public User confirm(String confirmationMessage) {
        ConfirmationMessage confirmationObject = confirmationRepository

                .findByCodeMessageAndExpiredAtAfter(confirmationMessage, LocalDateTime.now())
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Confirmation message is false or expired"));

        return confirmationObject.getUser();
    }
}
