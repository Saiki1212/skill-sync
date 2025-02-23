package com.stpl.tech.ss_service.ss_service.utilService.enumConverter;

import com.stpl.tech.ss_service.ss_service.modal.enums.DayType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DayTypeConverter implements AttributeConverter<DayType, String> {

    @Override
    public String convertToDatabaseColumn(DayType dayType) {
        if (dayType == null) {
            return null;
        }
        return dayType.getDayCode();
    }


    @Override
    public DayType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return DayType.fromCode(code);
    }

}
