package tests;

import vehicle.TeslaModelZ;
import bcatest.BCATestScenario;

public class Group2_4_TeslaDrive extends BCATestScenario {
    public int runTest() {
        TeslaModelZ c1 = new TeslaModelZ(70);

        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(-1);
        }, "Driving negative mileage should throw an exception");
        assertTrue(c1.canDrive(180), "canDrive should be true");

        c1.drive(0);
        assertEquals(c1.getRemainingRange(), 0, 0.1, "Mileage should be 0");

        c1.drive(50);
        assertEquals(c1.getRemainingRange(), 50, 0.1, "Mileage should be 50");

        c1.drive(110);
        assertEquals(c1.getRemainingRange(), 160, 0.1, "Mileage should be 160");
        assertEquals(c1.getRemainingRange(), 180, 0.1, "Remaining range should be 180");

        assertFalse(c1.canDrive(181), "canDrive should be false");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(180.1);
        }, "Driving more than the remaining range should throw an exception.");

        c1.drive(180);
        assertEquals(c1.getRemainingRange(), 0, 0.1, "Remaining range should be 0");

        return getFailedCount();
    }
}