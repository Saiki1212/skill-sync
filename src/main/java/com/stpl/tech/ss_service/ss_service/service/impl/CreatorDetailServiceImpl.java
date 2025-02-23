package com.stpl.tech.ss_service.ss_service.service.impl;

import com.stpl.tech.ss_service.ss_service.config.annotations.MasterTransactional;
import com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.CommonDaoResource;
import com.stpl.tech.ss_service.ss_service.dbDomain.repository.CreatorDetailRepo;
import com.stpl.tech.ss_service.ss_service.exception.CreatorException;
import com.stpl.tech.ss_service.ss_service.modal.dto.creator.CreatorDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.modal.entity.creator.CreatorDetailData;
import com.stpl.tech.ss_service.ss_service.modal.enums.CreatorStatus;
import com.stpl.tech.ss_service.ss_service.service.CreatorDetailService;
import com.stpl.tech.ss_service.ss_service.utilService.mapper.CreatorDetailMapper;
import com.stpl.tech.ss_service.ss_service.utilService.scheduler.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@MasterTransactional
public class CreatorDetailServiceImpl implements CreatorDetailService {

    @Autowired
    private CommonDaoResource commonDao;

    @Autowired
    private CreatorDetailRepo creatorDetailRepo;

    @Autowired
    private CreatorDetailMapper creatorDetailMapper;

    @Autowired
    private SchedulerService schedulerService;


    @Override
    public boolean createCreatorAccount(CreatorDetailDto creatorDetailDto, Integer userId) {
        CreatorDetailData creator = creatorDetailRepo.findByUserIdAndStatusNot(userId);

        if(creator != null) {
            validateCreatorBasedOnStatus(creator);
            // TODO --> check status in_active and remaining also.
        }

        creator = creatorDetailMapper.creatorDetailDtoToData(creatorDetailDto);
        creator.setCreatorStatus(CreatorStatus.CREATED);
        creator.setBiography(creatorDetailDto.getBio());
        creator.setProfileImageUrl(creatorDetailDto.getProfileImageUrl());
        UserBaseDetailData user = commonDao.find(UserBaseDetailData.class, userId);
        creator.setUserBaseDetailData(user);
        creatorDetailRepo.save(creator);
        schedulerService.scheduleUserEmailForCreatorConversion(user, 10);
        return true;
    }

    private void validateCreatorBasedOnStatus(CreatorDetailData creator) {
        if(CreatorStatus.SUSPICIOUS.equals(creator.getCreatorStatus()) || CreatorStatus.BLOCKED.equals(creator.getCreatorStatus())) {
            throw new CreatorException(
                    "Already account was in " + creator.getCreatorStatus() + " state , so please contact support",
                    "Previous account in " + creator.getCreatorStatus()
            );
        }
    }

    @Override
    @MasterTransactional
    public boolean editCreator(CreatorDetailDto creatorDto) {
        CreatorDetailData creator = commonDao.find(CreatorDetailData.class, creatorDto.getId());
        creator.setCreatorStatus(creatorDto.getCreatorStatus());
        commonDao.update(creator);
        return true;
    }

}
