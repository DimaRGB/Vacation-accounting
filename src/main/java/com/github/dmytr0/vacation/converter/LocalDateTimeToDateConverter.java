package com.github.dmytr0.vacation.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@WritingConverter
public class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {

    @Override
    public Date convert(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }
}
