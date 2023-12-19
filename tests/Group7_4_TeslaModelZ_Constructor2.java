package tests;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;

public class Group7_4_TeslaModelZ_Constructor2 extends BCATestScenario{

    @Override
    public int runTest() {
        TeslaModelZ t2 = new TeslaModelZ(30.0, 1); 

        assertEquals(t2.getMileage(), 30.0, .1, "Car's mileage should be 30."); 

        assertEquals(t2.getRemainingRange(), 340.0, .1, "Car's remaining range should be 310.0."); 

        assertEquals(t2.getMaxRange(), 340.0, .1, "Car's max range should be 340.0"); 

        assertEquals(t2.getModel(), "Z1", "Car's model should be Z1"); 

        assertEquals(t2.toString(), "Tesla Z1 (30.0 mi)", "toString is incorrect.");

        return getFailedCount();
    }
    
}
