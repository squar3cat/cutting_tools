package com.app.tools.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    public static final String DATE_PATTERN = "dd-MM-yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    private static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    private DateUtil() {
    }

    public static LocalDate atStartOfDayOrMin(LocalDate localDate) {
        return localDate != null ? LocalDate.from(localDate.atStartOfDay()) : MIN_DATE;
    }

    public static LocalDate atStartOfNextDayOrMax(LocalDate localDate) {
        return localDate != null ? LocalDate.from(localDate.plus(1, ChronoUnit.DAYS).atStartOfDay()) : MAX_DATE;
    }

    public static @Nullable LocalDate parseLocalDate(@Nullable String str) {
        return StringUtils.hasText(str) ? LocalDate.parse(str) : null;
    }

    public static String toString(LocalDate ldt) {
        return ldt == null ? "" : ldt.format(DATE_FORMATTER);
    }
}