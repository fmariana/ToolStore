package main.java.com.cardinalfinancial.programmingdemo;

import main.java.com.cardinalfinancial.programmingdemo.annotations.*;
import main.java.com.cardinalfinancial.programmingdemo.utilities.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The rental agreement
 */
public class Rental {
    private Tool tool;

    @Display(name = "Tool code")
    private String toolCode;

    @Display(name = "Tool type")
    private String toolType;

    @Display(name = "Tool brand")
    private String toolBrand;

    @Display(name = "Rental days")
    private int rentalDayCount;

    @Display(name = "Check out date",
            type = Display.DataType.DATE)
    private Date checkoutDate;

    @Display(name = "Due date",
            type = Display.DataType.DATE)
    private Date dueDate;

    @Display(name = "Daily rental charge",
            type = Display.DataType.CURRENCY)
    private BigDecimal dailyRentalCharge;

    @Display(name = "Charge days")
    private int actualDaysCharged;

    @Display(name = "Pre-discount charge",
            type = Display.DataType.CURRENCY)
    private BigDecimal preTotalAmount;

    @Display(name = "Discount percent",
            type = Display.DataType.PERCENT)
    private BigDecimal discountPercent;

    @Display(name = "Discount amount",
            type = Display.DataType.CURRENCY)
    private BigDecimal discountAmount;

    @Display(name = "Final charge",
            type = Display.DataType.CURRENCY)
    private BigDecimal totalAmount;

    /**
     * ctor
     * @param tool tool for this rental
     * @param checkoutDate start date for the rental
     * @param rentalDayCount days to rent
     * @param discountPercent discount percent 0-100
     */
    public Rental(Tool tool, Date checkoutDate, int rentalDayCount, double discountPercent) {
        this.tool = tool;
        this.checkoutDate = checkoutDate;
        this.rentalDayCount = rentalDayCount;
        this.discountPercent = BigDecimal.valueOf(discountPercent);
        init();
    }

    /**
     * Gets the checkout date as m/d/yy
     * @return date as a string, no time
     */
    public String getCheckoutDate() {
        return StringUtil.formatDate(checkoutDate);
    }

    /**
     * Sets the checkout date
     * @param checkoutDate beginning date for this rental
     */
    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     * Gets the number of days to rent
     * @return total number of days renting tool
     */
    public int getRentalDayCount() {
        return rentalDayCount;
    }

    /**
     * Sets rental days
     * @param rentalDayCount number of days of rent
     */
    public void setRentalDayCount(int rentalDayCount) {
        this.rentalDayCount = rentalDayCount;
    }

    /**
     * Gets discount percent 0-100
     * @return discount percent as 0-100
     */
    public double getDiscountPercent() {
        return discountPercent.doubleValue();
    }

    /**
     * Sets the discount percent
     * @param discountPercent number from 0-100
     */
    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = BigDecimal.valueOf(discountPercent);
    }

    /**
     * Get the tool code
     * @return tool code
     */
    public String getToolCode() {
        return toolCode;
    }

    /**
     * Get the tool type
     * @return tool type
     */
    public String getToolType() {
        return toolType;
    }

    /**
     * Get the tool brand
     * @return tool type
     */
    public String getToolBrand() {
        return toolBrand;
    }

    /**
     * Get the last date of rental
     * @return date formatted as m/d/yy
     */
    public String getDueDate() {
        return StringUtil.formatDate(dueDate);
    }

    /**
     * Gets the daily rental price
     * @return daily rental price
     */
    public double getDailyCharge() {
        return dailyRentalCharge.doubleValue();
    }

    /**
     * Returns the number of days rented minus free days
     * @return Number of days charged for rental
     */
    public int getDaysCharged() {
        return actualDaysCharged;
    }

    /**
     * Returns the pre-discount charge
     * @return pre-discount charge
     */
    public double getPreTotal() {
        return preTotalAmount.doubleValue();
    }

    /**
     * Amount deducted from pre-discount price
     * @return amount to deduct from price
     */
    public double getDiscountAmount() {
        return discountAmount.doubleValue();
    }

    /**
     * Gets the final price
     * @return final price after discounts
     */
    public double getFinalCharge() {
        return totalAmount.doubleValue();
    }

    /**
     * Calculates and sets the actual days charged
     * excludes weekends and holidays when tool type has no charge for those days
     */
    private void setActualDaysCharged() {
        int daysCharged = rentalDayCount;
        if (tool.getToolType().freeWeekends()) {
            daysCharged -= DateUtil.getWeekendDays(checkoutDate, rentalDayCount);
        }
        if (tool.getToolType().freeHolidays()) {
            daysCharged -= DateUtil.getHolidaysBetween(checkoutDate, dueDate);
        }
        actualDaysCharged = daysCharged;
    }

    /**
     * Calculates cost before discounts.
     */
    private void setPreTotalAmount() {
        preTotalAmount = dailyRentalCharge.multiply(new BigDecimal(actualDaysCharged));
    }

    /**
     * Calculates total amount to deduct from price
     */
    private void setDiscountAmount() {
        BigDecimal percentDecimal = discountPercent.divide(new BigDecimal(100));
        BigDecimal discount = preTotalAmount.multiply(percentDecimal);

        // round to 2 decimals
        discount = discount.setScale(2, BigDecimal.ROUND_HALF_UP);
        discountAmount = discount;
    }

    /**
     * Calculates final price, after discounts
     */
    private void setTotalAmount() {
        totalAmount = preTotalAmount.subtract(discountAmount);
    }

    /**
     * Formats all fields annotated with @Display annotation
     * as field name: field value. Field name = Display.name()
     * i.e. Tool code: XX
     *      Tool type: XX
     *      etc
     * @return Formatted rental agreement string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(Display.class)) {
                continue;
            }
            Display displayAnn = field.getAnnotation(Display.class);
            field.setAccessible(true);
            try {
                String key = displayAnn.name();
                String value = dataTypeFormat(displayAnn.type(), field.get(this));
                sb.append(StringUtil.formatKeyValuePair(key, value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * Initialize values
     */
    private void init() {
        toolCode = tool.getToolCode();
        toolType = tool.getToolType().toolTypeString();
        toolBrand = tool.getBrand();
        dueDate = DateUtil.addDaysToDate(checkoutDate, rentalDayCount);
        dailyRentalCharge = BigDecimal.valueOf(tool.getToolType().getDailyCharge());
        setActualDaysCharged();
        setPreTotalAmount();
        setDiscountAmount();
        setTotalAmount();
    }

    /**
     * Returns a string formatted with respect to its data type.
     * Date m/d/yy.  Percent n% currency $n
     * @param dataType type of data from Display annotations
     * @param obj obj to format
     * @return formatted string w respect to data type
     */
    private String dataTypeFormat(Display.DataType dataType, Object obj) {
        switch (dataType) {
            case DATE:
                return StringUtil.formatDate(obj);
            case PERCENT:
                return StringUtil.formatPercent(obj);
            case CURRENCY:
                return StringUtil.formatCurrency(obj);
            default:
                return obj.toString();
        }
    }
}
