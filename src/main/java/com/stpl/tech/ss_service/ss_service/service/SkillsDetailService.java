package com.stpl.tech.ss_service.ss_service.service;

import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserSkillMappingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillsDetailService {

    SkillCategoryDto createSkillCategory(SkillCategoryDto categoryDto);

    List<SkillCategoryDto> getSkillCategory(Integer categoryId);

    SkillDetailDto createSkillDetail(SkillDetailDto skillDetailDto);

    List<SkillDetailDto> getSkillDetails(Integer skillId);

    UserSkillMappingDto addSkillToAUser(UserSkillMappingDto mappingDto, Integer userId);

    List<UserSkillMappingDto> getAllUsersSkills(Integer skillId, Integer userId);

    UserSkillMappingDto editUserSkill(UserSkillMappingDto mappingDto);

    boolean removeUserSkill(Integer skillId, Integer userIdFromToken);
}
