package com.stpl.tech.ss_service.ss_service.modal.entity.creator;

import com.stpl.tech.ss_service.ss_service.modal.entity.BaseFieldsEntityData;
import com.stpl.tech.ss_service.ss_service.modal.enums.CommonStatus;
import com.stpl.tech.ss_service.ss_service.modal.enums.DayType;
import com.stpl.tech.ss_service.ss_service.utilService.enumConverter.DayTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "AVAILABLE_TIME_SLOTS")
public class AvailableTimeSlotsData extends BaseFieldsEntityData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIME_SLOT_ID")
    private Integer availableTimeSlotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_OF_COMMUNICATION_ID")
    private TypeOfCommunicationData typeOfCommunicationData;

    @Column(name = "DAY_OF_WEEK", nullable = false)
    @Convert(converter = DayTypeConverter.class)
    private DayType dayOfWeek;

    @Column(name = "START_TIME", nullable = false)
    private LocalTime startTime;

    @Column(name = "END_TIME", nullable = false)
    private LocalTime endTime;

    @Column(name = "DURATION_IN_MINUTES")
    private Long durationInMinutes;

    @Column(name = "TIME_SLOT_STATUS")
    @Enumerated(EnumType.STRING)
    private CommonStatus status;

    @PrePersist
    @PreUpdate
    private void validateTimeSlot() {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start time and end time cannot be null");
        }

        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        if (isDurationUpdateNeeded()) {
            this.durationInMinutes = Duration.between(startTime, endTime).toMinutes();
        }
    }

    private boolean isDurationUpdateNeeded() {
        return durationInMinutes == null || durationInMinutes != Duration.between(startTime, endTime).toMinutes();
    }

}
