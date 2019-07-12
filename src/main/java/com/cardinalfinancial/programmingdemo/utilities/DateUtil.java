package main.java.com.cardinalfinancial.programmingdemo.utilities;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Simple date utility operations
 */
public class DateUtil {

    /**
     * Checks if given date falls on saturday or sunday
     * @param date date to check
     * @return true if date is saturday or sunday
     */
    public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return isWeekend(cal);
    }

    /**
     * Checks if given date falls on a saturday or sunday
     * @param cal the calendar instance
     * @return true if date is saturday or sunday
     */
    public static boolean isWeekend(Calendar cal) {
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

    /**
     * Returns a count of all holidays within given date range, inclusive
     * @param startDate start date of range
     * @param endDate end date of range
     * @return number of holidays in between dates, 0 if none
     */
    public static int getHolidaysBetween(Date startDate, Date endDate) {
        int datesInRage = 0;
        List<Date> holidayDates = Holidays.getHolidays(startDate);
        for (Date holiday : holidayDates) {
            if (!(holiday.before(startDate) || holiday.after(endDate))) {
                datesInRage++;
            }
        }
        return datesInRage;
    }

    /**
     * Checks how many of the days fall on saturday or sunday, from given startdate
     * @param startDate date to start checking from
     * @param daysToCheck number of days to check, after start date
     * @return count of all weekend days
     */
    public static int getWeekendDays(Date startDate, int daysToCheck) {
        int weekendDayCount = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        for (int i = 0; i <= daysToCheck; i++) {
            cal.add(Calendar.DATE, i);
            if (isWeekend(cal)) {
               weekendDayCount++;
            }
        }
        return weekendDayCount;
    }

    /**
     * Adds the given number of days to the date.
     * @param date The start date
     * @param days number of days to add
     * @return new date after N days
     */
    public static Date addDaysToDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

}
