package org.libraryaccountingproject.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.hibernate.procedure.NoSuchParameterException;
import org.libraryaccountingproject.entities.ConfirmationMessage;
import org.libraryaccountingproject.entities.User;
import org.libraryaccountingproject.services.htmlTemlates.ConfirmationEmaileTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableAsync
public class EmailSendService {

    private final JavaMailSender mailSender;

    @Async
    public void sendConfirmationEmail(User user) throws MessagingException {

        String codeMessage = getCodeMessage(user);

        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(user.getEmail());
        helper.setSubject("Confirmation");
        helper.setText(ConfirmationEmaileTemplate.confirmationMessage(codeMessage, firstName, lastName));
    }

    private static String getCodeMessage(User user) {
        String codeMessage;

        Optional<ConfirmationMessage> newestMessage = user.getConfirmationMessage()
                .stream()
                .max(Comparator.comparing(message -> message.getExpiredAt().getChronology()));

        if (newestMessage.isPresent()) {
            codeMessage = newestMessage.get().getCodeMessage();
        } else {
            throw new NoSuchParameterException("No confirmation message found");
        }
        return codeMessage;
    }

}
