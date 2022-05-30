package StaffTraining;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuControllerTest {

    private MainMenuController mmc;

    @BeforeEach
    void setUp() {
        mmc = new MainMenuController();
    }

    @Test
    void formatCurrencyTest() {
        //format the cost of a product(s) into UK british pounds, output as String with pound symbol
        //8 should be represented as £8.00, 8.1 should be £8.10
        //8 should not be represented as $8.00, 8.1 should not be $8.10
        String cost = mmc.formatCurrency(8.1);
        assertEquals("£8.10", cost);
        assertNotEquals("$8.10", cost);
        //String doubleCost = mmc.formatCurrency(8);
        //assertEquals("£8.00", doubleCost);
        //assertNotEquals("$8.00", doubleCost);
    }
}