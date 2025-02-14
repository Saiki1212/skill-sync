package com.stpl.tech.ss_service.ss_service.service;

import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillEndorsementDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserSkillMappingDto;
import org.springframework.stereotype.Service;

@Service
public interface SkillsService {

    SkillCategoryDto createSkillCategory(SkillCategoryDto skillCategoryDto);

    boolean createSkillDetail(SkillDetailDto skillDetailDto);

    UserSkillMappingDto createSkillForUser(UserSkillMappingDto userSkillMappingDto);

    SkillEndorsementDto createSkillEndorsement(SkillEndorsementDto skillEndorsementDto);

}
