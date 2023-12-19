package tests;

import bcatest.BCATestScenario;
import vehicle.FordFrivolous;

public class Group7_3_FordFrivolous_Constructor1 extends BCATestScenario {
    public int runTest() {

        FordFrivolous c1 = new FordFrivolous();

        assertEquals(c1.getRemainingRange(), 0, .1, "Car should begin with 0 mileage");

        assertEquals(c1.toString(), "Ford Frivolous (0.0 mi)", "toString is correct");

        return getFailedCount();
    }

}
