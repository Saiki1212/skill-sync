package com.stpl.tech.ss_service.ss_service.utilService;

import java.util.Arrays;
import java.util.Objects;

public class NullCheckUtil {

    public static boolean isNull(Object... objects) {
        return Arrays.stream(objects).anyMatch(Objects::isNull);
    }

    public static boolean isNotNull(Object... objects) {
        return Arrays.stream(objects).noneMatch(Objects::isNull);
    }

}
