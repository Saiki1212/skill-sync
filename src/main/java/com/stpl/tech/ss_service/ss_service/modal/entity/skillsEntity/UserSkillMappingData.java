package com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity;

import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.modal.enums.SkillProficiencyLevelEnum;
import com.stpl.tech.ss_service.ss_service.modal.enums.UserSkillStatus;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_SKILLS_MAPPING_DATA")
public class UserSkillMappingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_SKILL_MAPPING_ID")
    private Integer userSkillMappingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKILL_ID")
    private SkillsDetailData skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserBaseDetailData user;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROFICIENCY_LEVEL")
    private SkillProficiencyLevelEnum proficiencyLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_SKILL_STATUS")
    private UserSkillStatus userSkillStatus;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userSkillMapping")
    private Set<SkillEndorsementData> skillEndorsementDataSet;

}
