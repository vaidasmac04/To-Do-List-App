package testers;

import java.time.LocalDateTime;

public class DateInputHandler {
	
	public static void check(String date){//method for complete date input handling
		
		checkFormat(date);
		int[] yearMonthDay = getYearMonthDay(date);
	
		checkIfNotPast(yearMonthDay[0], yearMonthDay[1], yearMonthDay[2]);
		
		checkYearMonthDay(yearMonthDay[0], yearMonthDay[1], yearMonthDay[2]);
	}
	
	public static int[] getYearMonthDay(String date) throws NumberFormatException{
		String[] temp = date.split("-");
		int[] yearMonthDay = {Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])};
		return yearMonthDay;
	}
	
	public static void checkFormat(String date) {
		if(date.length() != 10 || date.charAt(4) != '-' || date.charAt(7) != '-') {
			throw new InvalidInputException("Date format is incorrect. The correct format is: yyyy-mm-dd.");
		}
	}
	
	public static void checkYearMonthDay(int year, int month, int day) {
	    if(month > 12) {
			throw new InvalidInputException("Month cannot be bigger than 12");
		}
		else if(day > 31) {
			throw new InvalidInputException("Day cannot be bigger than 31");
		}
		else if(day > 30 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
			throw new InvalidInputException("This month do not have so many days.");
		}
		else if(month == 2 && year % 4 == 0 && day > 29) {
			throw new InvalidInputException("This month do not have so many days.");
		}
		else if(month == 2 && year % 4 != 0 && day > 28) {
			throw new InvalidInputException("This month do not have so many days.");
		}
	}
	
	private static void checkIfNotPast(int year, int month, int day) {
		LocalDateTime now = LocalDateTime.now();
		int nowYear, nowMonth, nowDay;
		nowYear = now.getYear();
		nowMonth = now.getMonthValue();
		nowDay = now.getDayOfMonth();
		
		if(nowYear > year) {
			throw new InvalidInputException("Deadline is already gone");
		}
		else if(nowYear == year && nowMonth > month) {
			throw new InvalidInputException("Deadline is already gone");
		}
		else if(nowMonth == month && nowDay > day) {
			throw new InvalidInputException("Deadline is already gone");
		}
	}
}
