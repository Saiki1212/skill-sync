package com.stpl.tech.ss_service.ss_service.utilService.scheduler;

import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.utilService.email.EmailService;
import com.stpl.tech.ss_service.ss_service.utilService.email.RegistrationSuccessEMail;
import com.stpl.tech.ss_service.ss_service.utilService.email.UserToCreatorConversionEmail;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class SchedulerServiceImpl implements SchedulerService{

    @Autowired
    private EmailService emailService;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Override
    public void scheduleUserEmailForRegistration(UserBaseDetailData userData, int ignoredDelayInMinutes) {
        taskScheduler.schedule(
                () ->  {
                    sendEmailForSuccessfulRegistration(userData);
                },
                Instant.now().plusSeconds(ignoredDelayInMinutes * 30L)
        );
        log.info("Email scheduled for: {} in {} minutes.", userData.getUsername(), ignoredDelayInMinutes);
    }


    @SneakyThrows
    private void sendEmailForSuccessfulRegistration(UserBaseDetailData userData) {
        RegistrationSuccessEMail email = new RegistrationSuccessEMail(userData);
        emailService.sendEmail(email);
    }

    @Override
    public void scheduleUserEmailForCreatorConversion(UserBaseDetailData userData, int ignoredDelayInMinutes) {
        taskScheduler.schedule(
                () ->  {
                    try {
                        emailService.sendEmail(new UserToCreatorConversionEmail(userData));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                Instant.now().plusSeconds(ignoredDelayInMinutes * 10L)
        );
        log.info("Email scheduled for: {} in {} minutes.", userData.getUsername(), ignoredDelayInMinutes);
    }

}
