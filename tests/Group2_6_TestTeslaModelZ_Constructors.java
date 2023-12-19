package tests;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;


public class Group2_6_TestTeslaModelZ_Constructors extends BCATestScenario {
    public int runTest() {
        TeslaModelZ t1 = new TeslaModelZ(0, 4);
        TeslaModelZ t2 = new TeslaModelZ(12.0, 4);
        TeslaModelZ t3 = new TeslaModelZ(4);

        assertEquals(t1.getMileage(), 0, 0.1, "Tesla's starting mileage should be 0.");
        assertEquals(t1.getModelNum(), 4, "Tesla's model number should be 4.");

        assertEquals(t2.getMileage(), 12.0, 0.1, "Tesla's starting mileage should be 12.0.");
        assertEquals(t2.getModel(), "Z4", "Model should be Z4.");
        assertEquals(t2.toString(), "Tesla Z4 (12.0 mi)", "toString should return 'Tesla Z4 (12.0 mi)'.");

        assertEquals(t3.getMileage(), 0, 0.1, "Mileage should be 0.");

        assertThrows(IllegalArgumentException.class, () -> {
            TeslaModelZ t4 = new TeslaModelZ(-1, 4);
        },
                "Negative starting mileage should throw an exception.");

        return getFailedCount();
    }

}