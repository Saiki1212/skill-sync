package com.stpl.tech.ss_service.ss_service.modal.entity;

import com.stpl.tech.ss_service.ss_service.modal.entity.creator.CreatorDetailData;
import com.stpl.tech.ss_service.ss_service.modal.enums.UserStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_BASE_DETAIL_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseDetailData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, unique = true)
    private Integer userId;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_STATUS")
    private UserStatus userStatus;


    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER", columnDefinition = "BIGINT")
    private Long phoneNumber;

    @OneToOne(mappedBy = "userBaseDetailData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CreatorDetailData creatorDetailData;

}
