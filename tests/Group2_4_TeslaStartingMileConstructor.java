package tests;

import vehicle.TeslaModelZ;
import bcatest.BCATestScenario;

public class Group2_4_TeslaStartingMileConstructor extends BCATestScenario{
    public int runTest(){
        TeslaModelZ c1 = new TeslaModelZ(50, 70);

        assertEquals(c1.toString(), "Tesla Z70 (50.0 mi)", "toString does not match");

        assertEquals(c1.getRemainingRange(), 340, 0.1, "Default remaining range should be 340");
        assertEquals(c1.getMaxRange(), 340, 0.1, "Max range should be 340");
        assertEquals(c1.getModel(), "Z70", "Model number should be 70");
        assertEquals(c1.getModelNum(), 70, 0.1, "Model number should be 70");

        assertThrows(IllegalArgumentException.class, () -> {new TeslaModelZ(-1, 70);}, "Mileage cannot be negative.");
        
        return getFailedCount();
    }
}