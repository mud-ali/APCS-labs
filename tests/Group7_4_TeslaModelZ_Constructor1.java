package tests;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;

public class Group7_4_TeslaModelZ_Constructor1 extends BCATestScenario {

    @Override
    public int runTest() {
        TeslaModelZ t1 = new TeslaModelZ(1);

        assertEquals(t1.getRemainingRange(), 0, .1, "Car should begin with 0 mileage.");

        assertEquals(t1.getRemainingRange(), 340.0, .1, "Car range should be 340.0.");

        assertEquals(t1.getMaxRange(), 340.0, .1, "Car's max range should be 340.0.");

        assertEquals(t1.getModelNum(), 1, .1, "Car's model number should be 1");

        assertEquals(t1.getModel(), "Z1", "Car's model should be Z1");

        assertEquals(t1.toString(), "Tesla Z1 (0.0 mi)", "toString is incorrect.");

        return getFailedCount();
    }

}
