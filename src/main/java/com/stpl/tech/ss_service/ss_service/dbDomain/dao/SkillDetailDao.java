package com.stpl.tech.ss_service.ss_service.dbDomain.dao;

import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.UserSkillMappingData;
import com.stpl.tech.ss_service.ss_service.modal.enums.UserSkillStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillDetailDao {

    List<UserSkillMappingData> findByUserIdAndSkillIdAndStatus(Integer userId, Integer skillId, List<UserSkillStatus> statusList);

    boolean removeUserSkill(Integer skillId, Integer userId, UserSkillStatus status);
}
