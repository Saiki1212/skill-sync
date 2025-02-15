package com.stpl.tech.ss_service.ss_service.resource.controller;

import com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.HttpServletRequestDetails;
import com.stpl.tech.ss_service.ss_service.resource.SSResourceUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SSResourceUtil.BASE_PATH + SSResourceUtil.USER_BASIC_DETAIL_ROOT_CONTEXT)
@Slf4j
public class UserBasicDetailController {

    @Autowired
    private HttpServletRequestDetails requestDetails;

    @GetMapping("test")
    public String getSample(HttpServletRequest request) {
        log.info("userId : " + requestDetails.getUserIdFromToken(request));
        return "Sample api end point " + requestDetails.getUserIdFromToken(request);
    }

}
