package main.java.com.cardinalfinancial.programmingdemo.tooltypes;

public class LadderToolType implements ToolType {

    public static final String LADDER_TYPE_STRING = "Ladder";
    private static final double LADDER_DAILY_CHARGE = 1.99;

    /**
     * Returns daily ladder charge
     * @return daily ladder charge
     */
    @Override
    public double getDailyCharge() {
        return LADDER_DAILY_CHARGE;
    }

    /**
     * No free weekends for ladders
     * @return True if weekends are free for ladder renters
     */
    @Override
    public boolean freeWeekends() {
        return false;
    }

    /**
     * Free holiday ladder rentals
     * @return True if holidays are free for ladder renters
     */
    @Override
    public boolean freeHolidays() {
        return true;
    }

    /**
     * Ladder type string literal
     * @return
     */
    @Override
    public String toolTypeString() {
        return LADDER_TYPE_STRING;
    }
}
