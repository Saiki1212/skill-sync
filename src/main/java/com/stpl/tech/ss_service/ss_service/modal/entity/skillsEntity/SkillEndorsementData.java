package com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity;

import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.modal.enums.SkillStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "SKILLS_ENDORSEMENT_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillEndorsementData {

    @Id
    @Column(name = "SKILL_ENDORSEMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skillEndorsementId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_SKILL_ID")
    private UserSkillMappingData userSkillMapping;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENDORSED_BY_USER_ID")
    private UserBaseDetailData endorsedByUser;

    @Column(name = "ENDORSEMENT_COMMENT")
    private String endorsementComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "ENDORSEMENT_STATUS")
    private SkillStatus endorsementStatus;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

}
