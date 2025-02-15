package com.stpl.tech.ss_service.ss_service.service;

import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import org.springframework.stereotype.Service;

@Service
public interface SkillsDetailService {

    SkillCategoryDto createSkillCategory(SkillCategoryDto categoryDto);

    SkillCategoryDto getSkillCategoryById(Integer categoryId);

    SkillDetailDto createSkillDetail(SkillDetailDto skillDetailDto);

    SkillDetailDto getSkillDetailsById(Integer skillId);

}
