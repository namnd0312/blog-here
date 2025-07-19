package com.namnd.utils;


import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author nam.nd6 on 12/7/2023
 */
public class DateTimeUtils {
    public final static String LOCAL_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public final static DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(LOCAL_DATE_TIME_FORMAT)
            .withZone(ZoneId.from(ZoneOffset.UTC));

    public static LocalDateTime stringToLocalDateTime(String strDate) {
        if (!StringUtils.hasText(strDate)) {
            return null;
        }
        return LocalDateTime.parse(strDate, LOCAL_DATE_TIME_FORMATTER);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return LOCAL_DATE_TIME_FORMATTER.format(localDateTime);
    }
}
