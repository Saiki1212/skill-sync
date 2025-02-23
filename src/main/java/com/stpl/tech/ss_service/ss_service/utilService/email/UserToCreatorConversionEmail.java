package com.stpl.tech.ss_service.ss_service.utilService.email;

import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.utilService.AppConstants;

import java.util.HashMap;
import java.util.Map;

public class UserToCreatorConversionEmail implements EmailTemplate {

    private final UserBaseDetailData userData;

    public UserToCreatorConversionEmail(UserBaseDetailData userData) {
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
        return "Conversion from user to creator";
    }

    @Override
    public String body() {
        return null;
    }

    @Override
    public Map<String, Object> data() {
        Map<String, Object> map = new HashMap<>();
        map.put("creatorId", userData.getCreatorDetailData().getCreatorId());
        map.put("username", userData.getUsername());
        return map;
    }

    @Override
    public String templateName() {
        return "user-creator-conversion";
    }

}
