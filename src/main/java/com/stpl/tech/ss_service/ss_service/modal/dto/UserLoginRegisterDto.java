package com.stpl.tech.ss_service.ss_service.modal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRegisterDto {
    private String username;
    private String password;
    private String email;
    private String name;
    private Long phoneNumber;
}
