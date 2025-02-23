package com.stpl.tech.ss_service.ss_service.utilService.mapper;

import com.stpl.tech.ss_service.ss_service.modal.dto.skillsDto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.skillsDto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.skillsDto.UserSkillMappingDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillCategoryData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillDetailData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.UserSkillMappingData;
import org.springframework.stereotype.Service;

@Service
public interface SkillDetailServiceMapper {

    SkillCategoryData convertSkillCategoryDtoToData(SkillCategoryDto skillCategoryDto);
    SkillCategoryDto convertSkillCategoryDataToDto(SkillCategoryData skillCategoryData, boolean isChildNeeded);

    SkillDetailData convertSkillDetailDtoToData(SkillDetailDto skillsDetailDto, SkillCategoryData skillCategoryData);
    SkillDetailDto convertSkillDetailDataToDto(SkillDetailData skillDetailData, boolean isParentNeeded);

    UserSkillMappingDto convertUserSkillMappingDataToDto(UserSkillMappingData mappingData);
    UserSkillMappingData convertUserSkillMappingDtoToData(UserSkillMappingDto mappingDto);

}
