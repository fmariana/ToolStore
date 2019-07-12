package main.java.com.cardinalfinancial.programmingdemo.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Common string formatting and conversions
 */
public class StringUtil {
    /**
     * The date format to be used across application
     */
    public final static String DATE_FORMAT = "M/d/yy";

    /**
     * Returns string with dollar symbol appended in front.
     * @param amount dollar amount
     * @return formatted string e.g. $3.39
     */
    public static String formatCurrency(Object amount) {
        return "$" + amount;
    }

    /**
     * Returns string with percent sign appended in the end
     * @param number the percentage
     * @return formatted string e.g. 53%
     */
    public static String formatPercent(Object number) {
        return number + "%";
    }

    /**
     * Returns string with simple date format M/d/yy
     * @param date date to format
     * @return formatted string e.g. 3/5/15
     */
    public static String formatDate(Object date) {
        SimpleDateFormat simpleDate = new SimpleDateFormat(DATE_FORMAT);
        return simpleDate.format(date);
    }

    /**
     * Returns a string of a key value pair formatted as key: value
     * @param key the key
     * @param val the value
     * @return formatted string e.g. name: john
     */
    public static String formatKeyValuePair(String key, String val) {
        return String.format("%s: %s\n", key, val);
    }

    /**
     * Returns a date object from string, if valid format.
     * @param dateStr the date as a string
     * @return date obj if valid. Null otherwise
     */
    public static Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date dateObj = null;
        try {
            dateObj = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateObj;
    }
}
