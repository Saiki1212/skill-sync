package com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface HttpServletRequestDetails {

    public String getUsernameFromToken(HttpServletRequest request);

}
