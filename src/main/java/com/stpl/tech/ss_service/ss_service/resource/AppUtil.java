package com.stpl.tech.ss_service.ss_service.resource;


import org.springframework.util.StringUtils;

public class AppUtil {

    public static boolean hasLength(String str) {
        return StringUtils.hasLength(str);
    }

    public static boolean hasNoLength(String str) {
        return !hasLength(str);
    }


}
