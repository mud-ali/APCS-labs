package tests;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;

public class Group3_2_SelfDriving extends BCATestScenario {

    @Override
    public int runTest() {
        TeslaModelZ c1 = new TeslaModelZ (70);

        assertThrows(IllegalArgumentException.class, () -> {c1.driveAutonomously(-1);}, 
                    "Driving negative mileage should throw IllegalArgumentException");

        c1.driveAutonomously(341);
        assertEquals(c1.getMileage(), 340, 0.01, "Mileage should be 340");

        return getFailedCount();
    }
    
}
