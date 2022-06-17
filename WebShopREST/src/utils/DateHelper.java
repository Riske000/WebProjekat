package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {
	
	public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
	
	
	public static String dateToString(LocalDate date) {
		return date.format(dateFormat);
	}
	
	
	public static LocalDate stringToDate(String date) {
		return LocalDate.parse(date, dateFormat);
	}
	
	

}
