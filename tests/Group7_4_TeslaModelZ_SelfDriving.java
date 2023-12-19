package tests;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;

public class Group7_4_TeslaModelZ_SelfDriving extends BCATestScenario {

    @Override
    public int runTest() {

        TeslaModelZ t4 = new TeslaModelZ(1);

        assertThrows(IllegalArgumentException.class, () -> {
            t4.driveAutonomously(-1);
        }, "Driving mileage cannot be neg.");

        t4.driveAutonomously(30);

        assertEquals(t4.getRemainingRange(), 30.0, .1, "Car should have 30.0 mileage.");

        t4.driveAutonomously(400);

        assertEquals(t4.getRemainingRange(), 340.0, .1, "Car should have 340.0 mileage.");

        return getFailedCount();
    }
}