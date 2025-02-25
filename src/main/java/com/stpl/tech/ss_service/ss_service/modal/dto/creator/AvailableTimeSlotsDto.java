package com.stpl.tech.ss_service.ss_service.modal.dto.creator;

import com.stpl.tech.ss_service.ss_service.modal.entity.creator.TypeOfCommunicationData;
import com.stpl.tech.ss_service.ss_service.modal.enums.CommonStatus;
import com.stpl.tech.ss_service.ss_service.modal.enums.DayType;
import com.stpl.tech.ss_service.ss_service.utilService.enumConverter.DayTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.CreatedBy;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AvailableTimeSlotsDto {

    private Integer availableTimeSlotId;
    private TypeOfCommunicationDto typeOfCommunicationDto;
    private DayType dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long durationInMinutes;
    private CommonStatus status;
    private LocalDateTime createdAt;
    private Integer createdBy;

}
