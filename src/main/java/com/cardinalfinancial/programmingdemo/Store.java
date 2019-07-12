package main.java.com.cardinalfinancial.programmingdemo;

import main.java.com.cardinalfinancial.programmingdemo.tooltypes.*;
import main.java.com.cardinalfinancial.programmingdemo.utilities.*;

import java.util.*;

/**
 * Store class for aggregation of tools and rental agreements
 */
public class Store {
    private Map<String, Tool> toolList = new HashMap<>();
    private List<Rental> rentals = new ArrayList<>();

    /**
     * Returns current list of rental agreements
     * @return list of rentals
     */
    public List<Rental> getRentals() {
        return this.rentals;
    }

    /**
     * Adds tool to inventory
     * @param toolCode tool code
     * @param toolType tool type string
     * @param brand brand of tool
     * @return true if successful. False if invalid tool type
     */
    public boolean addTool(String toolCode, String toolType, String brand) {
        ToolTypeFactory toolTypeFactory = new ToolTypeFactory();
        ToolType toolTypeObj = toolTypeFactory.getToolType(toolType);
        if (toolTypeObj != null) {
            Tool tool = new Tool(toolCode, toolTypeObj, brand);
            toolList.put(toolCode, tool);
            return true;
        }
        return false;
    }

    /**
     * Checks out and generates rental agreement.
     * Invalid arguments will throw InvalidArgumentException
     * @param toolCode tool code
     * @param rentalDayCount days to rent
     * @param discountPercent percent off from total price
     * @param checkoutDate rental start date
     * @return rental agreement
     */
    public Rental checkout(String toolCode, int rentalDayCount,
                            double discountPercent, String checkoutDate) {
        if (!toolList.containsKey(toolCode)) {
            throw new IllegalArgumentException("Tool does not exist: Invalid toolcode");
        }
        Date checkoutDateObj = StringUtil.parseDate(checkoutDate);
        if (rentalDayCount < 1) {
            throw new IllegalArgumentException("Rental day count is needs to be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100 ) {
            throw new IllegalArgumentException("Discount percentage needs to be in range 0-100");
        }
        if (checkoutDate == null) {
            throw new IllegalArgumentException("Invalid date. Please enter dd/MM/yy");
        }
        Tool tool = toolList.get(toolCode);
        Rental rental = new Rental(tool, checkoutDateObj, rentalDayCount, discountPercent);
        rentals.add(rental);
        System.out.println(rental.toString());
        return rental;
    }
}
