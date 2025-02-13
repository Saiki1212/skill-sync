package com.stpl.tech.ss_service.ss_service.utilService.email;

import org.springframework.stereotype.Service;

@Service
public interface EmailTemplate {

    String[] getToEmails();
    String getFromEmail();
    String subject();
    String body();

}
