package com.stpl.tech.ss_service.ss_service.modal.dto.skillsDto;

import com.stpl.tech.ss_service.ss_service.modal.enums.SkillStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDetailDto {

    private Integer skillId;
    private SkillCategoryDto skillsCategory;
    private String skillName;
    private String skillDescription;
    private SkillStatus skillStatus;
    private String requiredCertification;
    private LocalDateTime createdAt;
    private Integer createdBy;

}
