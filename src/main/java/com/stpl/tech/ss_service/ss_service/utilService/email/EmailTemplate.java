package com.stpl.tech.ss_service.ss_service.utilService.email;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface EmailTemplate {

    String[] getToEmails();
    String getFromEmail();
    String subject();
    String body();
    Map<String, Object> data();
    String templateName();

}
