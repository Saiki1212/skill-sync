package com.stpl.tech.ss_service.ss_service.modal.entity;

import com.stpl.tech.ss_service.ss_service.modal.enums.SkillStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "SKILLS_CATEGORY_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillCategoryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKILL_CATEGORY_ID")
    private Integer skillCategoryId;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @Column(name = "CATEGORY_DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY_EMP")
    private Integer createdBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY_STATUS")
    private SkillStatus categoryStatus;

//    @OneToMany(mappedBy = "skillCategoryData")
//    private Set<SkillsDetailData> skillDetailDataSet;

}
