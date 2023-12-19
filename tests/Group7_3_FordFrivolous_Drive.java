package tests;
import bcatest.BCATestScenario;
import vehicle.FordFrivolous;

public class  Group7_3_FordFrivolous_Drive extends BCATestScenario {
    public int runTest() {
        
        FordFrivolous c = new FordFrivolous();

        assertThrows(IllegalArgumentException.class, () -> {c.drive(-1);}, "Driving mileage cannot be neg.");

        c.drive(200);

        assertTrue(c.canDrive(50), "Should be able to drive 50");

        return getFailedCount();
    }
}

    






