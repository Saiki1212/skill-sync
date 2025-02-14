package com.stpl.tech.ss_service.ss_service.modal.dto;

import com.stpl.tech.ss_service.ss_service.modal.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicDataDto {
    private Integer userId;
    private String username;
    private UserStatus userStatus;
}
