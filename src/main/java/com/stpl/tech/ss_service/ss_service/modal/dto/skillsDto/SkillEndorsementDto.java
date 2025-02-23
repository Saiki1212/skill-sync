package com.stpl.tech.ss_service.ss_service.modal.dto.skillsDto;

import com.stpl.tech.ss_service.ss_service.modal.dto.UserBasicDataDto;
import com.stpl.tech.ss_service.ss_service.modal.enums.SkillStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillEndorsementDto {

    private Integer skillEndorsementId;
    private UserSkillMappingDto userSkillMapping;
    private UserBasicDataDto endorsedByUser;
    private String endorsementComment;
    private SkillStatus endorsementStatus;
    private LocalDateTime createdAt;

}
