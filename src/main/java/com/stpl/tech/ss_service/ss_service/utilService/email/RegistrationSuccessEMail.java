package com.stpl.tech.ss_service.ss_service.utilService.email;

import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;

public class RegistrationSuccessEMail implements EmailTemplate {
    private final String fromMail;
    private final UserBaseDetailData userData;

    public RegistrationSuccessEMail(UserBaseDetailData userData, String fromMail) {
        this.userData = userData;
        this.fromMail = fromMail;
    }

    @Override
    public String[] getToEmails() {
        return new String[]{userData.getEmail()};
    }

    @Override
    public String getFromEmail() {
        return fromMail;
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


}
