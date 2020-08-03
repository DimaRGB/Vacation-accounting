package com.github.dmytr0.vacation.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.LocalDate;

import static com.github.dmytr0.vacation.dto.Constants.DATE_FORMATTER;

@WritingConverter
public class LocalDateToStringConverter implements Converter<LocalDate, String> {

    @Override
    public String convert(LocalDate localDateTime) {
        return localDateTime.format(DATE_FORMATTER);
    }
}
