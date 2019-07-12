package main.java.com.cardinalfinancial.programmingdemo.tooltypes;

/**
 * The jackhammer tooltype
 */
public class JackhammerToolType implements ToolType {

    public static final String JACKHAMMER_TYPE_STRING = "Jackhammer";
    private static final double JACKHAMMER_DAILY_CHARGE = 2.99;

    /**
     * Daily charge of jackhammer.
     * @return daily charge of jackhammer
     */
    @Override
    public double getDailyCharge() {
        return JACKHAMMER_DAILY_CHARGE;
    }

    /**
     * Free weekends for jackhammer rentals
     * @return True if weekends are free of cost
     */
    @Override
    public boolean freeWeekends() {
        return true;
    }

    /**
     * Free holidays
     * @return True if holidays are free of cost
     */
    @Override
    public boolean freeHolidays() {
        return true;
    }

    /**
     * Jackhammer tool type string literal
     * @return
     */
    @Override
    public String toolTypeString() {
        return JACKHAMMER_TYPE_STRING;
    }
}
