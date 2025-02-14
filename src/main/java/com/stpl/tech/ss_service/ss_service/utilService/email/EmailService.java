package com.stpl.tech.ss_service.ss_service.utilService.email;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailTemplate email) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(email.getFromEmail());
        helper.setTo(email.getToEmails());
        helper.setSubject(email.subject());
        helper.setText(email.body(), true);

        mailSender.send(message);
        log.info("Email Sent Successfully with subject : {}", email.subject());
    }

}
