package tests;

import bcatest.BCATestScenario;
import vehicle.FordFrivolous;

public class Group2_3_TestFordFrivolousCreating extends BCATestScenario {
    public int runTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            FordFrivolous a = new FordFrivolous(-1);
        },
                "Entering negative mileage for Ford should throw an exception");

        FordFrivolous b = new FordFrivolous();
        assertEquals(b.getRemainingRange(), 0, 0.1, "Mileage should be zero");

        FordFrivolous c = new FordFrivolous(30);
        assertEquals(c.toString(), "Ford Frivolous (30.0 mi)", "toString does not match");
        assertEquals(c.getRemainingRange(), 30.0, 0.1, "Mileage should be 30.0");
        assertEquals(c.getMPG(), 23.6, 0.1, "Fuel capacity should be 20");
        assertEquals(c.getFuelCapacity(), 20, 0.1, "Fuel capacity should be 20");
        assertEquals(c.getFuelLevel(), 20, 0.1, "Fuel level should be 20");

        return getFailedCount();
    }
}