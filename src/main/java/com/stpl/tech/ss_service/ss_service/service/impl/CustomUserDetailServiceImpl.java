package com.stpl.tech.ss_service.ss_service.service.impl;

import com.stpl.tech.ss_service.ss_service.dbDomain.repository.UserBaseDetailRepo;
import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.modal.enums.UserStatus;
import com.stpl.tech.ss_service.ss_service.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CustomUserDetailServiceImpl implements CustomUserDetailService {

    @Autowired
    private UserBaseDetailRepo userBaseDetailRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserBaseDetailData userData = userBaseDetailRepo.findByUsername(username);
        if(Objects.isNull(userData)) {
            throw new UsernameNotFoundException("User not found for userName: " + username);
        } else if(UserStatus.DELETED.equals(userData.getUserStatus())) {
            log.info("User {} has been marked as deleted.", username);
            throw new UsernameNotFoundException("No Active user found with username: " + username);
        }

        return new User(userData.getUsername(), userData.getPassword(), List.of());
    }

}
