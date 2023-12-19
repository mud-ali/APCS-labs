package tests;

import bcatest.*;
import vehicle.*;

public class Group2_3_TestHondaAccordianDriving extends BCATestScenario {
    public int runTest() {
        HondaAccordian a = new HondaAccordian(2020);

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    a.drive(-1);
                },
                "Illegal Argument Exception not thrown");

        a.drive(0);

        assertEquals(a.getRemainingRange(), 0, 0.1, "Mileage should be 0");

        assertFalse(a.canDrive(100000), "Can Drive for 100000 should be false");
        assertTrue(a.canDrive(400), "Can Drive for 400 should be true");

        a.drive(400);

        assertEquals(a.getRemainingRange(), 81.4, 0.1, "Remaining range should be 81.4");

        a.drive(81.4);

        assertEquals(a.getFuelLevel(), 0, 0.1, "Remaining fuel should be 0");

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    a.drive(500);
                },
                "Illegal Argument Exception not thrown");

        a.refillTank();

        return getFailedCount();
    }
}