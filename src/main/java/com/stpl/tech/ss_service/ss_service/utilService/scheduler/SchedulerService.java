package com.stpl.tech.ss_service.ss_service.utilService.scheduler;

import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import org.springframework.stereotype.Service;

@Service
public interface SchedulerService {

    void scheduleUserEmailForRegistration(UserBaseDetailData userData, int ignoredDelayInMinutes);
    void scheduleUserEmailForCreatorConversion(UserBaseDetailData userData, int ignoredDelayInMinutes);

}
