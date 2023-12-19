package tests;

import vehicle.ChevroletBird;
import bcatest.BCATestScenario;

public class Group2_1_TestChevroletBird2_Flying extends BCATestScenario {
    public int runTest() {
        ChevroletBird b3 = new ChevroletBird();

        assertEquals(b3.getRemainingRange(), 250, 0.1, "getRemainingRange() should return 250");

        assertFalse(b3.checkWingsExtended(), "wings should be retracted");

        assertTrue(b3.canFly(0), "Can can fly 0 miles");

        assertThrows(IllegalArgumentException.class, () -> {
            b3.canFly(-20000);
        }, "Car cannot fly negative miles");

        b3.fly(250);

        assertEquals(b3.getRemainingRange(), 0.0, 0.1, "getRemainingRange() should return 0");

        assertTrue(b3.checkWingsExtended(), "wings should be extended");

        assertThrows(IllegalArgumentException.class, () -> {
            b3.drive(200);
        }, "Car does not have enough charge to drive 200 miles");

        b3.recharge();

        b3.drive(50);

        assertFalse(b3.checkWingsExtended(), "wings should be retracted");

        assertEquals(b3.getRemainingRange(), 200, 0.1, "getRemainingRange() should return 200");

        b3.drive(200);

        assertEquals(b3.getRemainingRange(), 0, 0.1, "getRemainingRange() should return 0");

        assertEquals(b3.getRemainingRange(), 250, 0.1, "getRemainingRange()) should return 250");

        return getFailedCount();
    }

}
