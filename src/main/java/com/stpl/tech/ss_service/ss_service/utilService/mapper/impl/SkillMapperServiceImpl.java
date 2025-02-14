package com.stpl.tech.ss_service.ss_service.utilService.mapper.impl;

import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillEndorsementDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserSkillMappingDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillCategoryData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillEndorsementData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillsDetailData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.UserSkillMappingData;
import com.stpl.tech.ss_service.ss_service.utilService.mapper.SkillMapperService;

public class SkillMapperServiceImpl implements SkillMapperService {

    @Override
    public SkillEndorsementDto convertSkillEndorsementDataToDto(SkillEndorsementData endorsementData) {
        return null;
    }

    @Override
    public SkillEndorsementData convertSkillEndorsementDtoToData(SkillEndorsementDto endorsementDto) {
        return null;
    }


    @Override
    public SkillDetailDto convertSkillDetailDataToDto(SkillsDetailData skillsDetailData) {
        return null;
    }

    @Override
    public SkillsDetailData convertSkillDetailDtoToData(SkillDetailDto skillsDetailDto) {
        return null;
    }


    @Override
    public UserSkillMappingDto convertUserSkillMappingDataToDto(UserSkillMappingData userSkillMappingData) {
        return null;
    }

    @Override
    public UserSkillMappingData convertUserSkillMappingDtoToData(UserSkillMappingDto userSkillMappingDto) {
        return null;
    }


    @Override
    public SkillCategoryDto convertSkillCategoryDataToDto(SkillCategoryData skillsDetailData) {
        SkillCategoryDto categoryDto = new SkillCategoryDto();
        categoryDto.setCategoryName(skillsDetailData.getCategoryName());
        categoryDto.setCategoryStatus(skillsDetailData.getCategoryStatus());
        categoryDto.setSkillCategoryId(skillsDetailData.getSkillCategoryId());
        categoryDto.setDescription(skillsDetailData.getDescription());
        categoryDto.setCreatedAt(skillsDetailData.getCreatedAt());
        categoryDto.setCreatedBy(skillsDetailData.getCreatedBy());

        return categoryDto;
    }

    @Override
    public SkillCategoryData convertSkillCategoryDtoToData(SkillCategoryDto skillCategoryDto) {
        SkillCategoryData categoryData = new SkillCategoryData();
        categoryData.setCategoryName(skillCategoryDto.getCategoryName());
        categoryData.setCategoryStatus(skillCategoryDto.getCategoryStatus());
        categoryData.setSkillCategoryId(skillCategoryDto.getSkillCategoryId());
        categoryData.setDescription(skillCategoryDto.getDescription());
        categoryData.setCreatedAt(skillCategoryDto.getCreatedAt());
        categoryData.setCreatedBy(skillCategoryDto.getCreatedBy());

        return categoryData;
    }

}
