package com.stpl.tech.ss_service.ss_service.modal.dto;

import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillEndorsementData;
import com.stpl.tech.ss_service.ss_service.modal.enums.SkillProficiencyLevelEnum;
import com.stpl.tech.ss_service.ss_service.modal.enums.UserSkillStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSkillMappingDto {

    private Integer userSkillMappingId;
    private SkillCategoryDto skill;
    private UserBasicDataDto user;
    private SkillProficiencyLevelEnum proficiencyLevel;
    private UserSkillStatus userSkillStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<SkillEndorsementDto> skillEndorsementDtoSet;

}
