package com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.impl;

import com.stpl.tech.ss_service.ss_service.config.jwtConfig.JWTService;
import com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.HttpServletRequestDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpServletRequestDetailsImpl implements HttpServletRequestDetails {

    @Autowired
    private JWTService jwtService;

    @Override
    public String getUsernameFromToken(HttpServletRequest request) {

        final String token = request.getHeader("Authorization") != null ?
                request.getHeader("Authorization").substring(7) : null;

        if(token == null) return null;

        return jwtService.getUserNameFromToken(token);
    }

}
