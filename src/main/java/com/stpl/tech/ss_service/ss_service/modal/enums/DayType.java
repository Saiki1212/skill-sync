package com.stpl.tech.ss_service.ss_service.modal.enums;

import java.util.HashMap;
import java.util.Map;

public enum DayType {

    SUNDAY("SUN"),
    MONDAY("MON"),
    TUESDAY("TUE"),
    WEDNESDAY("WED"),
    THURSDAY("THU"),
    FRIDAY("FRI"),
    SATURDAY("SAT");

    private final String dayCode;
    private static final Map<String, DayType> CODE_TO_ENUM = new HashMap<>();

    static {
        for (DayType day : values()) {
            CODE_TO_ENUM.put(day.dayCode, day);
        }
    }

    DayType(String dayCode) {
        this.dayCode = dayCode;
    }

    public String getDayCode() {
        return this.dayCode;
    }

    public static DayType fromCode(String code) {
        return CODE_TO_ENUM.get(code);
    }

}
