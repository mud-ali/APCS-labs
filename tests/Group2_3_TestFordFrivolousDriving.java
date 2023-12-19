package tests;

import bcatest.BCATestScenario;
import vehicle.*;

public class Group2_3_TestFordFrivolousDriving extends BCATestScenario{
    public int runTest() {
        FordFrivolous a = new FordFrivolous();

        assertThrows(
            IllegalArgumentException.class,
            () -> {a.drive(-1);},
            "Drive -1; Illegal Argument Exception not thrown"
        );

        a.drive(0);

        assertEquals(a.getMileage(), 0, 0.1, "Mileage should be 0");

        assertFalse(a.canDrive(100000), "Can Drive for 100000 should be false");
        assertTrue(a.canDrive(400), "Can Drive for 400 should be true");

        a.drive(400);
        assertEquals(a.getRemainingRange(), 72, 0.1, "Remaining range should be 72");

        a.driveAutonomously(36);

        assertEquals(a.getRemainingRange(), 0, 0.1, "Remaining range should be 0");

        assertEquals(a.getFuelLevel(), 0, 0.1, "Fuel level is 0");
/*
        assertThrows(
            IllegalArgumentException.class,
            () -> {a.driveAutonomously(500);},
            "Drive 500 - Illegal Argument Exception not thrown"
        );
*/
        a.refillTank();

        a.drive(106.2);

        assertEquals(a.getRemainingRange(), 365.8, 0.1, "Remaining range should be 365.8");

        assertFalse(a.canFly(360), "Cannot fly 360 miles");
        assertTrue(a.canFly(121.93), "Cannot fly 360 miles");

        assertThrows(
            IllegalArgumentException.class,
            () -> {a.fly(-10000);},
            "Flying - Illegal Argument Exception not thrown"
        );

        a.fly(5.8);

        assertEquals(a.getRemainingRange(), 348.4, 0.1, "Remaining should be 5.8");
        
        return getFailedCount();
    }
}