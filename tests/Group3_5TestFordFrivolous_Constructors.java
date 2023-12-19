package tests;

import bcatest.BCATestScenario;
import vehicle.FordFrivolous;

public class Group3_5TestFordFrivolous_Constructors extends BCATestScenario {
    @Override
    public int runTest() {
        FordFrivolous f1 = new FordFrivolous();
        assertEquals(f1.getRemainingRange(), 0, 0.1, "Default mileage should be 0");
        assertEquals(f1.getFuelCapacity(), 20, 0.1, "Fuel capacity should be 20");
        assertEquals(f1.getMPG(), 23.6, 0.1, "MPG should be 23.6");
        assertEquals(f1.getMake(), "Ford", "the make of f1 should be Ford");
        assertEquals(f1.getModel(), "Frivolous", "the make of f1 should be Frivolous");

        FordFrivolous f2 = new FordFrivolous(100.7);
        assertEquals(f2.getRemainingRange(), 100.7, 0.1, "Mileage should be 100.7");
        assertEquals(f2.getFuelCapacity(), 20, 0.1, "Fuel capacity should be 20");
        assertEquals(f2.getMPG(), 23.6, 0.1, "MPG should be 23.6");

        assertThrows(IllegalArgumentException.class, () -> {
            new FordFrivolous(-47);
        },
                "Setting negative mileage should throw IllegalArgumentException");

        return getFailedCount();
    }
}