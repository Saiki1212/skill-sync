package com.stpl.tech.ss_service.ss_service.modal.dto.skillsDto;

import com.stpl.tech.ss_service.ss_service.modal.enums.SkillStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillCategoryDto {
        private Integer skillCategoryId;

        private String categoryName;

        private String description;

        private LocalDateTime createdAt;

        private Integer createdBy;

        private SkillStatus categoryStatus;

        private Set<SkillDetailDto> skillDetailDtoSet;

}
