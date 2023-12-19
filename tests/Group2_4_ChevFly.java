package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group2_4_ChevFly extends BCATestScenario {
    public int runTest() {
        ChevroletBird c1 = new ChevroletBird(0);

        assertTrue(c1.canFly(50), "canFly should return true");
        c1.fly(50);

        assertTrue(c1.checkWingsExtended(), "checkWingsExtended should return true");
        assertEquals(c1.getRemainingRange(), 0, 0.1, "Mileage should still be 0");

        c1.drive(100);
        assertFalse(c1.checkWingsExtended(), "checkWingsExtended should return false");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.fly(101);
        },
                "Should throw exception if car cannot fly the given number of miles");
        assertThrows(IllegalArgumentException.class, () -> {
            c1.fly(-1);
        },
                "Should throw exception since the car cannot fly negative miles");

        return getFailedCount();
    }
}