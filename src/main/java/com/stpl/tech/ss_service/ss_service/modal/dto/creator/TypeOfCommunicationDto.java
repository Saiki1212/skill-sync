package com.stpl.tech.ss_service.ss_service.modal.dto.creator;

import com.stpl.tech.ss_service.ss_service.modal.entity.creator.AvailableTimeSlotsData;
import com.stpl.tech.ss_service.ss_service.modal.entity.creator.CreatorDetailData;
import com.stpl.tech.ss_service.ss_service.modal.enums.CommonStatus;
import com.stpl.tech.ss_service.ss_service.modal.enums.CommunicationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOfCommunicationDto {

    private Integer communicationId;
    private CreatorDetailDto creatorDetailDto;
    private CommonStatus communicationStatus;
    private CommunicationType communicationType;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private Set<AvailableTimeSlotsDto> availableTimeSlotsDtoSet;

}
