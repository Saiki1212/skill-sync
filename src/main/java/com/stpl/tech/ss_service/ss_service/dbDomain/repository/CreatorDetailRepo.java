package com.stpl.tech.ss_service.ss_service.dbDomain.repository;

import com.stpl.tech.ss_service.ss_service.modal.entity.creator.CreatorDetailData;
import com.stpl.tech.ss_service.ss_service.modal.enums.CreatorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreatorDetailRepo extends JpaRepository<CreatorDetailData, Integer> {

//    CreatorDetailData findByUserBaseDetailData_UserIdAndCreatorStatusNotIn(Integer userId, List<CreatorStatus> statuses);

    @Query("SELECT c FROM CreatorDetailData c WHERE c.userBaseDetailData.userId = :userId")
    CreatorDetailData findByUserIdAndStatusNot(@Param("userId") Integer userId);

}
