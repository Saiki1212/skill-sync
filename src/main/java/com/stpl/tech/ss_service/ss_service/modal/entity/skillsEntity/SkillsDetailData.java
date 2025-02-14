package com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "SKILLS_DETAIL_DATA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillsDetailData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKILL_ID")
    private Integer skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKILL_CATEGORY_ID")
    private SkillCategoryData skillCategoryData;

    @Column(name = "SKILL_NAME")
    private String skillName;

    @Column(name = "SKILL_DESCRIPTION")
    private String skillDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "SKILL_STATUS")
    private SkillStatus skillStatus;

    @Column(name = "CERTIFICATION_REQUIRED")
    private String requiredCertification = "N";

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "CREATED_BY_EMP")
    private Integer createdBy;

}








