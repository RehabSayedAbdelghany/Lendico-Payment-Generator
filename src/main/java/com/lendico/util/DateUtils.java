package com.lendico.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
/**
 * Utility class that contains the needed operation for date parsing,formatting, other date operation
 * @author rehab.sayed
 *
 */
public class DateUtils {
	
	private DateUtils() {}
	public static  LocalDateTime getLocalDateTime(final Date date) {
    	final Instant instant = date.toInstant();
		final ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
    }
	
	public static Date getDate(String dateStr) {
		final Instant instant = Instant.parse(dateStr);
        final LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        return Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
	}
}
