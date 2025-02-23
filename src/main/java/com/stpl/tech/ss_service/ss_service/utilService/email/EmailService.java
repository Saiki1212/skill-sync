package com.stpl.tech.ss_service.ss_service.utilService.email;

import io.micrometer.common.util.StringUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;


    public void sendEmail(EmailTemplate email) throws Exception {

        String emailContent = generateEmailContent(email.templateName(), email.data());

        if(emailContent == null) {
            emailContent = email.body();
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(email.getFromEmail());
        helper.setTo(email.getToEmails());
        helper.setSubject(email.subject());
        helper.setText(emailContent, true);

        mailSender.send(message);
        log.info("Email Sent Successfully with subject : {}", email.subject());
    }

    private String generateEmailContent(String templateName, Map<String, Object> data) {
        if(StringUtils.isNotBlank(templateName)) {
            return null;
        }
        Context context = new Context();
        context.setVariables(data);
        return templateEngine.process(templateName, context);
    }

}
