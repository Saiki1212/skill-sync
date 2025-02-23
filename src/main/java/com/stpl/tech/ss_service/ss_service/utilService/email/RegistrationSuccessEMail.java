package com.stpl.tech.ss_service.ss_service.utilService.email;

import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.utilService.AppConstants;

import java.util.Map;

public class RegistrationSuccessEMail implements EmailTemplate {

    private final UserBaseDetailData userData;

    public RegistrationSuccessEMail(UserBaseDetailData userData) {
        this.userData = userData;
    }

    @Override
    public String[] getToEmails() {
        return new String[]{userData.getEmail()};
    }

    @Override
    public String getFromEmail() {
        return AppConstants.SEND_EMAIL_FROM;
    }

    @Override
    public String subject() {
        return "Registration successful brother";
    }

    @Override
    public String body() {
        return "Your registration was completed !!!! " +
                "tq for registering " + userData.getFullName() +
                "Your user name is  " + userData.getUsername() + " and phone number is " + userData.getPhoneNumber();
    }

    @Override
    public Map<String, Object> data() {
        return null;
    }

    @Override
    public String templateName() {
        return null;
    }


}
