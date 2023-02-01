package com.connect.brick.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeUtils {

	public static String getUpdatedDate(LocalDateTime now, LocalDateTime posted) {
		
		LocalDateTime tempDateTime = LocalDateTime.from( now );

		long years = tempDateTime.until( posted, ChronoUnit.YEARS);
		tempDateTime = tempDateTime.plusYears( years );

		long months = tempDateTime.until( posted, ChronoUnit.MONTHS);
		tempDateTime = tempDateTime.plusMonths( months );

		long days = tempDateTime.until( posted, ChronoUnit.DAYS);
		tempDateTime = tempDateTime.plusDays( days );

		long hours = tempDateTime.until( posted, ChronoUnit.HOURS);
		tempDateTime = tempDateTime.plusHours( hours );

		long minutes = tempDateTime.until( posted, ChronoUnit.MINUTES);
		tempDateTime = tempDateTime.plusMinutes( minutes );

		long seconds = tempDateTime.until( posted, ChronoUnit.SECONDS);
		
		if(years!=0)
			return years+"년";
		else if(months!=0)
			return months+"개월";
		else if(days!=0)
			return days+"일";
		else if(hours!=0)
			return hours+"시간";
		else if(minutes!=0)
			return minutes+"분";
		else if(seconds!=0)
			return seconds+"초";
		else
			return 0+"초";
		
	}
	
	public static String getTimeFormatDate(LocalDateTime posted) {
		
		return posted.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	
	public static String getFormatDateForDate(LocalDate posted) {
		
		return posted.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

}
