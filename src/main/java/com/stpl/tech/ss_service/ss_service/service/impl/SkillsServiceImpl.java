package com.stpl.tech.ss_service.ss_service.service.impl;

import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillEndorsementDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserSkillMappingDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillCategoryData;
import com.stpl.tech.ss_service.ss_service.dbDomain.repository.SkillCreationRepository;
import com.stpl.tech.ss_service.ss_service.service.SkillsService;
import com.stpl.tech.ss_service.ss_service.utilService.mapper.SkillMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SkillsServiceImpl implements SkillsService {

    @Autowired
    private SkillMapperService skillMapperService;

    @Autowired
    private SkillCreationRepository skillRepo;

    @Override
    public SkillCategoryDto createSkillCategory(SkillCategoryDto skillCategoryDto) {

        SkillCategoryData categoryData = skillMapperService.convertSkillCategoryDtoToData(skillCategoryDto);
        categoryData = skillRepo.save(categoryData);

        return skillMapperService.convertSkillCategoryDataToDto(categoryData);
    }

    @Override
    public boolean createSkillDetail(SkillDetailDto skillDetailDto) {
        return false;
    }

    @Override
    public UserSkillMappingDto createSkillForUser(UserSkillMappingDto userSkillMappingDto) {
        return null;
    }

    @Override
    public SkillEndorsementDto createSkillEndorsement(SkillEndorsementDto skillEndorsementDto) {
        return null;
    }

}
