package com.stpl.tech.ss_service.ss_service.config.jpaAuditing;

import com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.HttpServletRequestDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Integer> {

    private final HttpServletRequest request;

    @Autowired
    private HttpServletRequestDetails servletRequestDetails;

    public AuditorAwareImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Integer userId = servletRequestDetails.getUserIdFromToken(request);
        return Optional.ofNullable(userId);
    }

}
