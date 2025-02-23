package com.stpl.tech.ss_service.ss_service.modal.entity.creator;

import com.stpl.tech.ss_service.ss_service.modal.entity.BaseFieldsEntityData;
import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.modal.enums.CreatorStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CREATOR_DETAIL_DATA")
public class CreatorDetailData extends BaseFieldsEntityData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CREATOR_ID")
    private Integer creatorId;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private UserBaseDetailData userBaseDetailData;

    @Column(name = "BIO")
    private String biography;

    @Column(name = "PROFILE_IMAGE")
    private String profileImageUrl;

    @Column(name = "HEADER_IMAGE")
    private String headerProfileImageUrl;

    @Column(name = "CREATOR_STATUS")
    @Enumerated(EnumType.STRING)
    private CreatorStatus creatorStatus;

    @OneToMany(mappedBy = "creatorDetailData")
    private Set<TypeOfCommunicationData> typeOfCommunicationDataSet = new HashSet<>();

}
