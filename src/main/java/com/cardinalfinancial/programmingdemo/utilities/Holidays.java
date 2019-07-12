package main.java.com.cardinalfinancial.programmingdemo.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Known holidays. Currently - 4th of july and labor day
 */
public class Holidays {
    /**
     * Returns the observed date of 4th of july in the year of given date.
     * If date falls on a saturday, roll back to friday, if sunday, return monday
     * @param date Date containing target year
     * @return the observed date
     */
    public static Date independenceDay(Date date) {
        int day = 4;
        Calendar cal = Calendar.getInstance();

        // exact date
        cal.setTime(date);
        cal.set(Calendar.MONTH, Calendar.JULY);
        cal.set(Calendar.DATE, day);

        // observed date
        // go back to friday if saturday, to monday if sunday
        int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(dayOfTheWeek == Calendar.SATURDAY){
            cal.set(Calendar.DATE, day - 1);
        }
        else if (dayOfTheWeek == Calendar.SUNDAY) {
            cal.set(Calendar.DATE, day + 1);
        }
        return cal.getTime();
    }

    /**
     * Returns the first monday of september in the given year.
     * @param date Date containing target year.
     * @return labor day date
     */
    public static Date laborDay(Date date) {
        // first monday
        int dayOfWeekInMonth = 1;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, dayOfWeekInMonth);
        return cal.getTime();
    }

    /**
     * Returns list of all known holidays as dates.
     * @param date Date containing target year.
     * @return list of dates
     */
    public static List<Date> getHolidays(Date date) {
        List<Date> holidays = new ArrayList<>();
        holidays.add(independenceDay(date));
        holidays.add(laborDay(date));
        return holidays;
    }

}
