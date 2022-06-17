package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
	
	public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
	
	
	public static String dateTimeToString(LocalDateTime dateTime) {
		return dateTime.format(dateTimeFormat);
	}
	
	
	public static LocalDateTime stringToDateTime(String dateTime) {
		return LocalDateTime.parse(dateTime, dateTimeFormat);
	}
	
	

}
