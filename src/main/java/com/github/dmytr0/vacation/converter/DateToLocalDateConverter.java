package com.github.dmytr0.vacation.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDate;

import static com.github.dmytr0.vacation.dto.Constants.DATE_FORMATTER;

@ReadingConverter
public class DateToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}
