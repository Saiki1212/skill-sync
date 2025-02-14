package com.stpl.tech.ss_service.ss_service.dbDomain.repository;

import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillCategoryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillCreationRepository extends JpaRepository<SkillCategoryData, Integer> {
}
