package tests;

import vehicle.TeslaModelZ;
import bcatest.BCATestScenario;

public class Group2_4_TeslaSelfDrive extends BCATestScenario{
    public int runTest(){
        TeslaModelZ c1 = new TeslaModelZ(70);

        assertThrows(IllegalArgumentException.class, () -> {c1.driveAutonomously(-10);}, "Cannot drive negative miles");

        c1.driveAutonomously(340);
        assertEquals(c1.getMileage(), 340, 0.1, "Mileage should be 340");
        assertEquals(c1.getRemainingRange(), 0, 0.1, "Remaining range should be 0");

        c1.recharge();
        c1.driveAutonomously(341);
        assertEquals(c1.getMileage(), 680, 0.1, "Mileage should be 680");
        assertEquals(c1.getRemainingRange(), 0, 0.1, "Remaining range should be 0");

        return getFailedCount();
    }
}
