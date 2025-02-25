package com.stpl.tech.ss_service.ss_service.modal.entity.creator;

import com.stpl.tech.ss_service.ss_service.modal.entity.BaseCreateEntityData;
import com.stpl.tech.ss_service.ss_service.modal.enums.CommonStatus;
import com.stpl.tech.ss_service.ss_service.modal.enums.CommunicationType;
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

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TYPE_OF_COMMUNICATION_DATA")
public class TypeOfCommunicationData extends BaseCreateEntityData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMUNICATION_ID")
    private Integer communicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID")
    private CreatorDetailData creatorDetailData;

    @Column(name = "COMMUNICATION_STATUS")
    @Enumerated(EnumType.STRING)
    private CommonStatus communicationStatus;

    @Column(name = "COMMUNICATION_TYPE")
    private CommunicationType communicationType;

    @OneToMany(mappedBy = "typeOfCommunicationData")
    private Set<AvailableTimeSlotsData> availableTimeSlotsDataSet = new HashSet<>();

}
