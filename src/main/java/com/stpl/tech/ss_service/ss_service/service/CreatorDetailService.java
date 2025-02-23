package com.stpl.tech.ss_service.ss_service.service;

import com.stpl.tech.ss_service.ss_service.modal.dto.creator.CreatorDetailDto;
import org.springframework.stereotype.Service;

@Service
public interface CreatorDetailService {

    boolean createCreatorAccount(CreatorDetailDto creatorDetailDto, Integer userId);

    boolean editCreator(CreatorDetailDto creatorDto);
}
