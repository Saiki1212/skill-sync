package com.stpl.tech.ss_service.ss_service.modal.dto.creator;

import com.stpl.tech.ss_service.ss_service.modal.dto.UserBasicDataDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.modal.entity.creator.TypeOfCommunicationData;
import com.stpl.tech.ss_service.ss_service.modal.enums.CreatorStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatorDetailDto {

    private Integer id;
    private UserBasicDataDto userBasicDataDto;
    private String bio;
    private String profileImageUrl;
    private String headerProfileImageUrl;
    private CreatorStatus creatorStatus;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
    private Set<TypeOfCommunicationDto> typeOfCommunicationDtoSet;

}
