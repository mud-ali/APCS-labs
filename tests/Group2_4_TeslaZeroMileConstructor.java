package tests;

import vehicle.TeslaModelZ;
import bcatest.BCATestScenario;

public class Group2_4_TeslaZeroMileConstructor extends BCATestScenario{
    public int runTest(){
        TeslaModelZ c1 = new TeslaModelZ(70);

        assertEquals(c1.getMileage(), 0, 0.1, "Default mileage should be 0");
        assertEquals(c1.getRemainingRange(), 340, 0.1, "Default remaining range should be 340");
        assertEquals(c1.getMaxRange(), 340, 0.1, "Miles on max charge should be 340");

        assertEquals(c1.toString(), "Tesla Z70 (0.0 mi)", "toString does not match");

        assertEquals(c1.getMake(), "Tesla", "Make does not match");
        assertEquals(c1.getModel(), "Z70", "Model does not match");
        assertEquals(c1.getModelNum(), 70, 0.1, "Model number does not match");


        return getFailedCount();
    }
}