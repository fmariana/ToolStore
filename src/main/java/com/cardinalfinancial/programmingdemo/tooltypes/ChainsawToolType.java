package main.java.com.cardinalfinancial.programmingdemo.tooltypes;

/**
 * The chainsaw tooltype.
 */
public class ChainsawToolType implements ToolType {
    public static final String CHAINSAW_TYPE_STRING = "Chainsaw";
    private static final double CHAINSAW_DAILY_CHARGE = 1.49;

    /**
     * The chainsaw daily charge
     * @return the daily chainsaw charge
     */
    @Override
    public double getDailyCharge() {
        return CHAINSAW_DAILY_CHARGE;
    }

    /**
     * Free chainsaw weekends
     * @return True if weekends are free of cost
     */
    @Override
    public boolean freeWeekends() {
        return true;
    }

    /**
     * No free holidays
     * @return True if holidays are free of charge
     */
    @Override
    public boolean freeHolidays() {
        return false;
    }

    /**
     * Chainsaw tool type string literal
     * @return
     */
    @Override
    public String toolTypeString() {
        return CHAINSAW_TYPE_STRING;
    }
}
