//Authors: Vijay Tummalapenta and Parth Kabaria
package tests;
import vehicle.FordFrivolous;


public class Group3_6_TestFordFrivolous1_Constructor1 extends bcatest.BCATestScenario{
    
    @Override
    public int runTest(){
        FordFrivolous c1 = new FordFrivolous(1000);

        assertEquals(c1.getMileage(), 1000, 0.1, "Initial mileage is not correct (should be 1000)");
        
        assertEquals(c1.getFuelCapacity(), 20, 0.1, "Fuel Capacity is not correct (should be 20)");

        assertEquals(c1.getFuelLevel(), 20, 0.1, "Remaining fuel level is not correct (should be 20)");

        assertEquals(c1.getMPG(), 23.6, 0.1, "MPG is incorrect (should be 23.6)");

        assertEquals(c1.getRemainingRange(), 472, 0.1, "Remaining range is not correct (should be 472)");

        assertEquals(c1.toString(), "Ford Frivolous (1000.0 mi)", "toString does not work as intended");

        assertEquals(c1.getMake(), "Ford", "Make is incorrect (should be Ford)");

        assertEquals(c1.getModel(), "Frivolous", "Model is incorrect (should be Frivolous)");


        return getFailedCount();
    }
}
