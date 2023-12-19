//Authors: Vijay Tummalapenta and Parth Kabaria
package tests;
import vehicle.TeslaModelZ;

public class Group3_6_TestTeslaModelZ2_Constructor2 extends bcatest.BCATestScenario {

    @Override
    public int runTest(){
        TeslaModelZ c1 = new TeslaModelZ(1001);

        assertEquals(c1.getMileage(), 0, 0.1, "Initial mileage is not correct (should be 0)");
        
        assertEquals(c1.getMaxRange(), 340, 0.1, "Range is not correct (should be 340)");

        assertEquals(c1.getModelNum(), 1001, 0.0, "Model Number is not correct (should be 1001)");

        assertEquals(c1.getRemainingRange(), 340, 0.1, "Remaining range is not correct (should be 340)");

        assertEquals(c1.toString(), "Tesla Z1001 (0.0 mi)", "toString does not work as intended");

        assertEquals(c1.getMake(), "Tesla", "Make is incorrect (should be Tesla)");

        assertEquals(c1.getModel(), "Z1001", "Model is incorrect (should be 1001");


        return getFailedCount();
    }
}
