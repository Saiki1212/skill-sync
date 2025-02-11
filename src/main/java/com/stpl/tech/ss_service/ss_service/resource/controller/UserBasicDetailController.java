package com.stpl.tech.ss_service.ss_service.resource.controller;

import com.stpl.tech.ss_service.ss_service.resource.SSResourceUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SSResourceUtil.BASE_PATH + SSResourceUtil.USER_BASIC_DETAIL_ROOT_CONTEXT)
public class UserBasicDetailController {

    @GetMapping("test")
    public String getSample() {
        return "Sample api end point";
    }

}
