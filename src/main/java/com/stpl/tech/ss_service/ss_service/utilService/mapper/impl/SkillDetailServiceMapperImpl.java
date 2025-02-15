package com.stpl.tech.ss_service.ss_service.utilService.mapper.impl;

import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserSkillMappingDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillCategoryData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillDetailData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.UserSkillMappingData;
import com.stpl.tech.ss_service.ss_service.modal.enums.SkillStatus;
import com.stpl.tech.ss_service.ss_service.utilService.mapper.SkillDetailServiceMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class SkillDetailServiceMapperImpl implements SkillDetailServiceMapper {

    @Override
    public SkillCategoryData convertSkillCategoryDtoToData(SkillCategoryDto dto) {

        SkillCategoryData categoryData = new SkillCategoryData();
        categoryData.setCategoryName(dto.getCategoryName());
        categoryData.setCategoryStatus(SkillStatus.ACTIVE);
        categoryData.setDescription(dto.getDescription());
        categoryData.setCreatedAt(LocalDateTime.now());
        categoryData.setCreatedBy(dto.getCreatedBy());
        return categoryData;
    }

    @Override
    public SkillCategoryDto convertSkillCategoryDataToDto(SkillCategoryData data, boolean isChildNeeded) {

        SkillCategoryDto categoryDto = new SkillCategoryDto();
        categoryDto.setCategoryName(data.getCategoryName());
        categoryDto.setCategoryStatus(data.getCategoryStatus());
        categoryDto.setDescription(data.getDescription());
        categoryDto.setCreatedAt(data.getCreatedAt());
        categoryDto.setCreatedBy(data.getCreatedBy());
        if(isChildNeeded) {
            Set<SkillDetailDto> detailDtoSet = new HashSet<>();
            for(SkillDetailData skillDetailData : data.getSkillDetailDataSet()) {
                detailDtoSet.add(convertSkillDetailDataToDto(skillDetailData, false));
            }
            categoryDto.setSkillDetailDtoSet(detailDtoSet);
        }
        return categoryDto;

    }


    @Override
    public SkillDetailData convertSkillDetailDtoToData(SkillDetailDto dto, SkillCategoryData categoryData) {

        SkillDetailData detailData = new SkillDetailData();
        detailData.setSkillName(dto.getSkillName());
        detailData.setSkillDescription(dto.getSkillDescription());
        detailData.setSkillStatus(SkillStatus.ACTIVE);
        detailData.setCreatedBy(dto.getCreatedBy());
        detailData.setCreatedAt(LocalDateTime.now());
        detailData.setSkillCategoryData(categoryData);
        return detailData;

    }

    @Override
    public SkillDetailDto convertSkillDetailDataToDto(SkillDetailData data, boolean isParentNeeded) {

        SkillDetailDto dto = new SkillDetailDto();
        dto.setSkillName(data.getSkillName());
        dto.setSkillDescription(data.getSkillDescription());
        dto.setSkillStatus(data.getSkillStatus());
        dto.setCreatedBy(data.getCreatedBy());
        dto.setCreatedAt(data.getCreatedAt());
        if(isParentNeeded) {
            dto.setSkillsCategory(convertSkillCategoryDataToDto(data.getSkillCategoryData(), false));
        }
        return dto;

    }

    @Override
    public UserSkillMappingDto convertUserSkillMappingDataToDto(UserSkillMappingData mappingData) {

        UserSkillMappingDto mappingDto = new UserSkillMappingDto();
        mappingDto.setSkillDetailDto(convertSkillDetailDataToDto(mappingData.getSkill(), true));
        mappingDto.setUserSkillMappingId(mappingData.getUserSkillMappingId());
        mappingDto.setUserSkillStatus(mappingData.getUserSkillStatus());
        mappingDto.setCreatedAt(mappingData.getCreatedAt());

        return mappingDto;
    }

    @Override
    public UserSkillMappingData convertUserSkillMappingDtoToData(UserSkillMappingDto mappingDto) {
        return null;
    }

}
