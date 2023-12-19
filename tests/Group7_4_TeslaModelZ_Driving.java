package tests;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;

public class Group7_4_TeslaModelZ_Driving extends BCATestScenario {

    @Override
    public int runTest() {
        TeslaModelZ t3 = new TeslaModelZ(1);

        assertThrows(IllegalArgumentException.class, () -> {
            t3.drive(-1);
        }, "Driving mileage cannot be neg.");

        assertEquals(t3.getRemainingRange(), 0.0, .1, "Car should have 0.0 mileage.");

        t3.drive(30);

        assertEquals(t3.getRemainingRange(), 30.0, .1, "Car should have 30.0 mileage.");

        t3.drive(300);

        assertEquals(t3.getRemainingRange(), 330.0, .1, "Car should have 330.0 mileage.");

        assertEquals(t3.getRemainingRange(), 10.0, .1, "Car should have 10.0 remaining miles");

        assertFalse(t3.canDrive(11), "Should not be able to drive 11 miles.");

        assertTrue(t3.canDrive(10), "Should be able to drive 10 miles.");

        assertThrows(IllegalArgumentException.class, () -> {
            t3.drive(11);
        }, "Cannot drive 11 miles, exceeds max range.");

        return getFailedCount();
    }

}
