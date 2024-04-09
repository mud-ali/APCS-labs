package tests;

import bcatest.BCATestScenario;
import vehicle.FordFrivolous;

public class TestFordFrivolous extends BCATestScenario {

    @Override
    public int runTest() {
        FordFrivolous c1 = new FordFrivolous(50);

        assertEquals(c1.getFuelLevel(), 20.0, .1, "Gas should be full when initialized");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.driveAutonomously(-1);
        }, "Driving miles cannot be negative.");

        c1.driveAutonomously(100);
        assertEquals(c1.getOdometerMiles(), 150, .1, "Odometer should be 150 after first drive.");
        assertEquals(c1.getFuelLevel(), 20 - 2 * (100 / 23.6), .1, "Fuel level should be 11.5 after first drive.");

        c1.drive(100);
        assertEquals(c1.getOdometerMiles(), 250, .1, "Odometer should be 250 after first drive.");
        assertEquals(c1.getFuelLevel(), 20 - 3 * (100 / 23.6), .1, "Fuel level should be 7.3 after first drive.");

        c1.driveAutonomously(300);
        assertEquals(c1.getOdometerMiles(), 336, .1, "Drives 86 more miles, until empty.");

        c1.refillTank();
        c1.driveAutonomously(500);
        assertEquals(c1.getOdometerMiles(), 572, .1, "Drives 236 miles automously on a full tank.");

        //////////////////////
        // Flying
        //
        FordFrivolous c2 = new FordFrivolous(2000);

        assertEquals(c2.toString(), "Ford Frivolous (2000.0 mi)", "toString does not match");

        assertEquals(c2.getOdometerMiles(), 2000, .1, "Odometer should be 2000 when initialized.");
        assertEquals(c2.getFuelLevel(), 20, .1, "Gas should be full when initialized.");

        assertThrows(IllegalArgumentException.class, () -> {
            c2.canFly(-1);
        }, "Can fly miles cannot be negative.");

        assertTrue(c2.canFly(157), "Flying 157 should succeed.");
        assertFalse(c2.canFly(157.45), "Flying 157.45 should fail.");
        assertFalse(c2.canFly(158), "Flying 158 should fail.");

        assertThrows(IllegalArgumentException.class, () -> {
            c2.fly(-1);
        }, "Flying miles cannot be negative.");

        c2.fly(149);
        assertEquals(c2.getOdometerMiles(), 2000, .1, "Odometer should be 2000 after first fly.");
        assertEquals(c2.getFuelLevel(), 1.1, .1, "Fuel level should be about 1.1 after first fly.");

        assertThrows(IllegalArgumentException.class, () -> {
            c2.fly(30);
        }, "Not enough fuel to fly.");

        assertTrue(c2.canFly(0), "Flying 0 should succeed.");

        return getFailedCount();
    }
}
