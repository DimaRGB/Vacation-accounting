package com.github.dmytr0.vacation.utils;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class CommonUtils {

    @Value("${vacation.days.per.year:24}")
    private int vacationDaysPerYear;
    private LocalDate resetDay;

    @Value("${reset.day}")
    private void setLocalDate(String localDateStr) {
        if (isEmpty(localDateStr) || !localDateStr.matches("[0-3]?\\d\\.[0-1]?\\d")) {
            throw new IllegalArgumentException("Invalid 'reset.day' property. Must match format: dd.MM (e.g. 31.03)");
        }
        resetDay = LocalDate.parse(localDateStr + ".00", DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    public LocalDate endOfYear(int year) {
        return LocalDate.of(year, 12, 31);
    }

    public LocalDate startOfYear(int year) {
        return LocalDate.of(year, 1, 1);
    }

    public LocalDate min(LocalDate... dates) {
        if (dates == null || dates.length == 0) {
            throw new IllegalArgumentException("min");
        }
        return Stream.of(dates).min(LocalDate::compareTo).get();
    }

    public LocalDate max(LocalDate... dates) {
        if (dates == null || dates.length == 0) {
            throw new IllegalArgumentException("max");
        }
        return Stream.of(dates).max(LocalDate::compareTo).get();
    }

    public int daysDiff(LocalDate start, LocalDate end) {
        return (int) DAYS.between(start, end) + 1;
    }

    public boolean isAfterReset(LocalDate calcDate) {
        return calcDate.isAfter(getResetDate(calcDate));
    }

    public boolean isBeforeReset(LocalDate calcDate) {
        LocalDate resetDate = getResetDate(calcDate);
        return calcDate.isBefore(resetDate) || calcDate.isEqual(resetDate);
    }

    public LocalDate getResetDate(LocalDate calcDate) {
        return resetDay.withYear(calcDate.getYear());
    }

    public LocalDate getResetDate(int year) {
        return resetDay.withYear(year);
    }

    public <T, Y> void printMap(String message, Map<T, Y> object, Comparator<T> comparator) {
        System.out.println("----- " + message + " -----");
        object.entrySet().stream()
                .sorted((e1, e2) -> comparator.compare(e1.getKey(), e2.getKey()))
                .forEach(System.out::println);
    }

    public float calculateVacationDays(int allDays) {
        return (allDays * vacationDaysPerYear) / 365f;
    }
}
