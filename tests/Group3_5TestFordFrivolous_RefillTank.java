package tests;

import bcatest.BCATestScenario;
import vehicle.FordFrivolous;

public class Group3_5TestFordFrivolous_RefillTank extends BCATestScenario {
    @Override
    public int runTest() {
        FordFrivolous f1 = new FordFrivolous();
        f1.drive(17.1);
        f1.refillTank();
        assertEquals(f1.getFuelLevel(), f1.getFuelCapacity(), 0.1, "fuel level should be at the full fuel capacity");
        f1.drive(236);
        f1.refillTank(5.7);
        assertEquals(f1.getFuelLevel(), 20 - (236 / f1.getMPG()) + 5.7, 0.1, "fuel level should be 15.7");
        assertThrows(IllegalArgumentException.class, () -> {
            f1.refillTank(10);
        },
                "trying to refill tank past max fuel capacity should throw IllegalArgumentException");
        assertThrows(IllegalArgumentException.class, () -> {
            f1.refillTank(-1);
        },
                "trying to refill tank with a negative amount should throw IllegalArgumentException");
        return getFailedCount();
    }
}