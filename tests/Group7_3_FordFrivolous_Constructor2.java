package tests;

import bcatest.BCATestScenario;
import vehicle.FordFrivolous;

public class Group7_3_FordFrivolous_Constructor2 extends BCATestScenario {
    public int runTest() {

        assertThrows(IllegalArgumentException.class, () -> {
            FordFrivolous c1 = new FordFrivolous(-1);
        }, "Driving mileage cannot be neg.");

        FordFrivolous c2 = new FordFrivolous(10);

        assertEquals(c2.getMake(), "Ford", "Should have created a Ford");
        assertEquals(c2.getModel(), "Frivolous", "Should have created a Frivolous");
        assertEquals(c2.getRemainingRange(), 10, 0.1, "Car should begin with 0 mileage");
        assertEquals(c2.getFuelCapacity(), 20, 0.1, "Fuel capacity should be 20 gallons");
        assertEquals(c2.getFuelLevel(), 20, 0.1, "Fuel capacity should be 20 gallons");
        assertEquals(c2.getMPG(), 23.6, 0.1, "MPG should be 23.6");
        assertEquals(c2.getRemainingRange(), (472), 0.1, "Remaining range should be 472");
        assertEquals(c2.toString(), "Ford Frivolous (10.0 mi)", "toString is correct");

        return getFailedCount();
    }

}
