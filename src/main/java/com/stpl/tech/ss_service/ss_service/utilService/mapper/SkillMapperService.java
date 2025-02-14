package com.stpl.tech.ss_service.ss_service.utilService.mapper;

import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillEndorsementDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserSkillMappingDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillEndorsementData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillCategoryData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillsDetailData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.UserSkillMappingData;

public interface SkillMapperService {

    SkillEndorsementDto convertSkillEndorsementDataToDto(SkillEndorsementData endorsementData);
    SkillEndorsementData convertSkillEndorsementDtoToData(SkillEndorsementDto endorsementDto);

    SkillDetailDto convertSkillDetailDataToDto(SkillsDetailData skillsDetailData);
    SkillsDetailData convertSkillDetailDtoToData(SkillDetailDto skillsDetailDto);

    UserSkillMappingDto convertUserSkillMappingDataToDto(UserSkillMappingData userSkillMappingData);
    UserSkillMappingData convertUserSkillMappingDtoToData(UserSkillMappingDto userSkillMappingDto);

    SkillCategoryDto convertSkillCategoryDataToDto(SkillCategoryData skillsDetailData);
    SkillCategoryData convertSkillCategoryDtoToData(SkillCategoryDto skillCategoryDto);

}
