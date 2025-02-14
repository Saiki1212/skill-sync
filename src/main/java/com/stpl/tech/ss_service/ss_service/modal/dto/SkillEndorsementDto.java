package com.stpl.tech.ss_service.ss_service.modal.dto;

import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.UserSkillMappingData;
import com.stpl.tech.ss_service.ss_service.modal.enums.SkillStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
