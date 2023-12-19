package tests;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;

public class Group3_2_Tesla1_WithoutMileage extends BCATestScenario {

    @Override
    public int runTest() {
        TeslaModelZ c1 = new TeslaModelZ(70);

        assertEquals(c1.getRemainingRange(), 0, 0.01, "Default mileage should be zero");
        assertEquals(c1.getMaxRange(), 340, 0.01, "Max range should be 340");
        assertEquals(c1.getModelNum(), 70, 0.01, "Model number should be 70");
        assertEquals(c1.getModel(), "Z70", "Model should be Z70");
        assertEquals(c1.getMake(), "Tesla", "Make shoud be Tesla");
        assertEquals(c1.getRemainingRange(), 340, 0.01, "Remaining range should be 340");
        assertEquals(c1.toString(), "Tesla Z70 (0.0 mi)", "Should return \"Tesla Z70 (0.0 mi)\"");

        return getFailedCount();
    }

}
