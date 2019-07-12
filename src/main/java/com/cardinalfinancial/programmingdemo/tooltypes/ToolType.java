package main.java.com.cardinalfinancial.programmingdemo.tooltypes;

/**
 * The tooltype interface
 */
public interface ToolType {
    /**
     * Daily charge for this type of tool
     * @return decimal daily charge, not including free days
     */
    double getDailyCharge();

    /**
     * Returns whether weekends are free or not.
     * @return true if free weekends
     */
    boolean freeWeekends();

    /**
     * Returns whether holidays (fourth of july & labor day) are free.
     * @return true if free holidays
     */
    boolean freeHolidays();

    /**
     * Returns a string of the type of tool
     * @return ToolType as a string e.g. "ladder"
     */
    String toolTypeString();
}
