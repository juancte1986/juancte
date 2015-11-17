package ar.edu.uces.progweb2.agenda.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarUtils {
	
	private static final String FORMAT = "dd/MM/yyyy";
	
	public static Date[] getWeek(Date date) {
		Date[] week = new Date[7];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);// convert date object to calendar object
		int day = calendar.get(Calendar.DAY_OF_WEEK); // day (Sunday = 1)1 to 7
		if (day == 1) {
			week[0] = calendar.getTime(); // Sunday
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 2) {
			calendar.add(Calendar.DAY_OF_WEEK, -1); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 3) {
			calendar.add(Calendar.DAY_OF_WEEK, -2); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 4) {
			calendar.add(Calendar.DAY_OF_WEEK, -3); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 5) {
			calendar.add(Calendar.DAY_OF_WEEK, -4); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 6) {
			calendar.add(Calendar.DAY_OF_WEEK, -5); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		} else if (day == 7) {
			calendar.add(Calendar.DAY_OF_WEEK, -6); // Sunday
			week[0] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Monday
			week[1] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Tuesday
			week[2] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Wednesday
			week[3] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Thursday
			week[4] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Friday
			week[5] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_WEEK, 1);// Saturday
			week[6] = calendar.getTime();
		}
		return week;
	}

	public static Date getDate(int actualPage, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_WEEK, actualPage);
		return calendar.getTime();
	}
	
	public static Date getDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		Date dateFormat = null;
		try {
			dateFormat = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateFormat;
	}
	public static Date getDateTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT + " HH:mm");
		Date dateFormat = null;
		try {
			dateFormat = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateFormat;
	}
	
	public static boolean isValidDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		try {
			sdf.setLenient(false);
			sdf.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isAfterDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		try {
			sdf.setLenient(false);
			sdf.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static String getTime(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(time);
	}

	public static String convertDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		return sdf.format(date);
	}
	
	public static List<String> getHours(){
		List<String> hours= new ArrayList<String>();
		hours.add("00:00");
		hours.add("00:30");
		hours.add("01:00");
		hours.add("01:30");
		hours.add("02:00");
		hours.add("02:30");
		hours.add("03:00");
		hours.add("03:30");
		hours.add("04:00");
		hours.add("04:30");
		hours.add("05:00");
		hours.add("05:30");
		hours.add("06:00");
		hours.add("06:30");
		hours.add("07:00");
		hours.add("07:30");
		hours.add("08:00");
		hours.add("08:30");
		hours.add("09:00");
		hours.add("09:30");
		hours.add("10:00");
		hours.add("10:30");
		hours.add("11:00");
		hours.add("11:30");
		hours.add("12:00");
		hours.add("12:30");
		hours.add("13:00");
		hours.add("13:30");
		hours.add("14:00");
		hours.add("14:30");
		hours.add("15:00");
		hours.add("15:30");
		hours.add("16:00");
		hours.add("16:30");
		hours.add("17:00");
		hours.add("17:30");
		hours.add("18:00");
		hours.add("18:30");
		hours.add("19:00");
		hours.add("19:30");
		hours.add("20:00");
		hours.add("20:30");
		hours.add("21:00");
		hours.add("21:30");
		hours.add("22:00");
		hours.add("22:30");
		hours.add("23:00");
		hours.add("23:30");
		return hours;
	}

	public static List<String> convertDateToString(Date[] week) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		List<String> list = new ArrayList<String>();
		for(Date date : week){
			list.add(sdf.format(date));
		}
		return list;
	}

	public static Date getDate(Date date, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT + " HH:mm");
		Date dateReturn = new Date();
		try {
			dateReturn = sdf.parse(convertDateToString(date) +" "+ time);
		} catch (ParseException e) {
		}
		return dateReturn;
	}
}

