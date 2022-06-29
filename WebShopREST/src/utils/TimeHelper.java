package utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeHelper {
	
	public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
	
	
	public static String timeToString(LocalTime time) {
		return time.format(timeFormat);
	}
	
	
	public static LocalTime stringToTime(String time) {
		return LocalTime.parse(time, timeFormat);
	}
	
	

}
