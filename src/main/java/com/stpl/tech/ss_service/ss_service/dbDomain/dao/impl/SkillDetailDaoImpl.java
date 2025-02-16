package com.stpl.tech.ss_service.ss_service.dbDomain.dao.impl;

import com.stpl.tech.ss_service.ss_service.dbDomain.dao.SkillDetailDao;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.UserSkillMappingData;
import com.stpl.tech.ss_service.ss_service.modal.enums.SkillStatus;
import com.stpl.tech.ss_service.ss_service.modal.enums.UserSkillStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillDetailDaoImpl implements SkillDetailDao {

    @Autowired
    private EntityManager manager;


    @Override
    @SuppressWarnings("unchecked")
    public List<UserSkillMappingData> findByUserIdAndSkillIdAndStatus(Integer userId, Integer skillId, List<UserSkillStatus> statusList) {
        StringBuilder queryString = new StringBuilder("FROM UserSkillMappingData " +
                "WHERE user.userId = :userId AND userSkillStatus NOT IN (:statusList) ");
        if(skillId != null) {
            queryString.append(" AND skill.skillId = :skillId ORDER BY userSkillMappingId DESC");
        }
        Query query = manager.createQuery(queryString.toString());
        query.setParameter("userId", userId)
                .setParameter("statusList", statusList);
        if(skillId != null) {
            query.setParameter("skillId", skillId);
        }
        return query.getResultList();
    }


    @Override
    public boolean removeUserSkill(Integer skillId, Integer userId, UserSkillStatus status) {
        Query query = manager.createQuery("UPDATE UserSkillMappingData " +
                "SET userSkillStatus =: status WHERE user.userId = :userId AND skill.skillId = :skillId");

        query.setParameter("skillId", skillId)
                .setParameter("userId", userId)
                .setParameter("status", status);

        return query.executeUpdate() > 0;
    }

    @Override
    public boolean removeAEndorsedSkill(Integer endorseId, Integer userId) {
        Query query = manager.createQuery("UPDATE SkillEndorsementData " +
                "SET endorsementStatus = :status " +
                "WHERE skillEndorsementId = :endorseId AND endorsedByUser.userId = :userId");

        query.setParameter("endorseId", endorseId)
                .setParameter("userId", userId)
                .setParameter("status", SkillStatus.DELETED);
        return query.executeUpdate() > 0;
    }

    @Override
    public Integer isAlreadySkillAddedToUser(Integer userId, Integer skillId, UserSkillStatus status) {
        Query query = manager.createQuery("SELECT userSkillMappingId UserSkillMappingData " +
                "WHERE skill.skillId = :skillId AND user.userId = :userId AND userSkillStatus <>:status");

        query.setParameter("skillId", skillId)
                .setParameter("userId", userId)
                .setParameter("status", status);
        List<Integer> data = query.getResultList();
        return data.isEmpty() ? null : data.get(0);
    }

}
