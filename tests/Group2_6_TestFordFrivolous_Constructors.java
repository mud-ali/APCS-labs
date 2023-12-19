package tests;

import bcatest.BCATestScenario;
import vehicle.FordFrivolous;

public class Group2_6_TestFordFrivolous_Constructors extends BCATestScenario {
    public int runTest() {
        FordFrivolous t1 = new FordFrivolous(0.0);
        FordFrivolous t2 = new FordFrivolous(10.1);
        FordFrivolous t3 = new FordFrivolous();

        assertEquals(t1.getRemainingRange(), 0.0, 0.1, "Starting mileage for t1 should be 0.");

        assertEquals(t2.getRemainingRange(), 10.1, 0.1, "Starting mileage for t2 should be 10.1.");
        assertEquals(t3.getRemainingRange(), 0.0, 0.1, "Starting mileage for t3 should be 0.");
        assertThrows(IllegalArgumentException.class, () -> {
            FordFrivolous t4 = new FordFrivolous(-1);
        },
                "Negative starting mileage should throw an exception.");

        return getFailedCount();
    }
}