package com.stpl.tech.ss_service.ss_service.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface CustomUserDetailService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

}
