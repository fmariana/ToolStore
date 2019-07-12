package test.java.com.cardinalfinancial.programmingdemo;

import main.java.com.cardinalfinancial.programmingdemo.Rental;
import main.java.com.cardinalfinancial.programmingdemo.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {

    // todo add test data here
    @Test
    public void invalidDiscountThrowsException() {
        Store store = new Store();
        store.addTool("JAKR", "JACKHAMMER", "Rigid");
        assertThrows(IllegalArgumentException.class, () -> {
            store.checkout("JAKR", 5, 101, "9/3/15");
        });
    }

    @Test
    public void testLadderFourthOfJulyAndDiscount() {
        Store store = new Store();
        store.addTool("LADW","LADDER","Werner");
        Rental rental = store.checkout("LADW", 3, 10, "7/2/20");
        assertEquals("7/5/20", rental.getDueDate());
        assertEquals(1.99, rental.getDailyCharge());
        assertEquals(2, rental.getDaysCharged());
        assertEquals(3.98, rental.getPreTotal());
        assertEquals(10, rental.getDiscountPercent());
        assertEquals(0.40, rental.getDiscountAmount());
        assertEquals(3.58, rental.getFinalCharge());
    }

    @Test
    public void testChainsawFourthOfJulyAndDiscount() {
        Store store = new Store();
        store.addTool("CHNS", "chainsaw", "Stihl");
        Rental rental = store.checkout("CHNS", 5, 25, "7/2/15");
        assertEquals("7/7/15", rental.getDueDate());
        assertEquals(1.49, rental.getDailyCharge());
        assertEquals(3, rental.getDaysCharged());
        assertEquals(4.47, rental.getPreTotal());
        assertEquals(25, rental.getDiscountPercent());
        assertEquals(1.12, rental.getDiscountAmount());
        assertEquals(3.35, rental.getFinalCharge());
    }

    @Test
    public void testJackhammerLaborDayNoDiscount() {
        Store store = new Store();
        store.addTool("JAKD", "jackhammer", "Dewalt");
        Rental rental = store.checkout("JAKD", 6, 0, "9/3/15");
        assertEquals("9/9/15", rental.getDueDate());
        assertEquals(2.99, rental.getDailyCharge());
        assertEquals(3, rental.getDaysCharged());
        assertEquals(8.97, rental.getPreTotal());
        assertEquals(0, rental.getDiscountPercent());
        assertEquals(0, rental.getDiscountAmount());
        assertEquals(8.97, rental.getFinalCharge());
    }

    @Test
    public void testJackhammerFourthOfJulyNoDiscount() {
        Store store = new Store();
        store.addTool("JAKR", "jackhammer", "Rigid");
        Rental rental = store.checkout("JAKR", 9, 0, "7/2/15");
        assertEquals("7/11/15", rental.getDueDate());
        assertEquals(2.99, rental.getDailyCharge());
        assertEquals(5, rental.getDaysCharged());
        assertEquals(14.95, rental.getPreTotal());
        assertEquals(0, rental.getDiscountPercent());
        assertEquals(0, rental.getDiscountAmount());
        assertEquals(14.95, rental.getFinalCharge());
    }

    @Test
    public void testJackhammerFourthOfJulyAndDiscount() {
        Store store = new Store();
        store.addTool("JAKR", "jackhammer", "Rigid");
        Rental rental = store.checkout("JAKR", 4, 50, "7/2/20");
        assertEquals("7/6/20", rental.getDueDate());
        assertEquals(2.99, rental.getDailyCharge());
        assertEquals(1, rental.getDaysCharged());
        assertEquals(2.99, rental.getPreTotal());
        assertEquals(50, rental.getDiscountPercent());
        assertEquals(1.50, rental.getDiscountAmount());
        assertEquals(1.49, rental.getFinalCharge());
    }
}