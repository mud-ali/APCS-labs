package tests;

import bcatest.BCATestScenario;
import vehicle.HondaAccordian;

public class TestHondaAccordian extends BCATestScenario {

    public int runTest() {
        HondaAccordian c1 = new HondaAccordian(2018);

        assertEquals(c1.getOdometerMiles(), 0, .1, "Default mileage should be zero.");
        assertEquals(c1.getFuelCapacity(), 14.5, .1, "Initial fuel capacity not correct.");
        assertEquals(c1.getFuelCapacity(), c1.getFuelLevel(), .1, "The car should begin full.");
        assertEquals(c1.getMPG(), 33.2, .1, "Initial mpg not correct.");
        assertEquals(c1.getRemainingRange(), c1.getFuelCapacity() * c1.getMPG(), .1,
                "Remaining range of car not correct at creation.");
        assertEquals(c1.toString(), "2018 Honda Accordian (0.0 mi)", "toString does not match");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(-1);
        }, "Driving mileage cannot be negative.");

        assertTrue(c1.canDrive(30), "canDrive should be true");
        c1.drive(30);
        assertEquals(c1.getOdometerMiles(), 30, .1, "Mileage should be 30 after first drive.");

        c1.drive(200);
        assertEquals(c1.getOdometerMiles(), 230, .1, "Mileage should be 230 after second drive.");

        assertEquals(c1.getRemainingRange(), c1.getFuelCapacity() * c1.getMPG() - 230, .1,
                "Remaining range of car not correct after driving twice.");

        assertFalse(c1.canDrive(252), "Driving 252 should fail.");
        assertTrue(c1.canDrive(251), "Driving 251 should succeed.");

        c1.drive(251);
        assertEquals(c1.getOdometerMiles(), 481, .1, "Mileage should be 481 after third drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(5);
        }, "Driving beyond empty should fail.");

        ///////////////
        // Testing refill
        //
        assertThrows(IllegalArgumentException.class, () -> {
            c1.refillTank(-1);
        }, "Refilling tank with a negative should throw an exception");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.refillTank(15);
        }, "Overfilling tank should throw an exception");

        c1.refillTank(14);

        assertTrue(c1.getRemainingRange() > 33.2 * 14,
                "After refilling tank with 14 gallons, range should be at least 464.8 miles.");

        c1.refillTank();

        assertEquals(c1.getRemainingRange(), c1.getMPG() * c1.getFuelCapacity(), 0.1,
                "After topping off tank, car should be at max range.");

        return getFailedCount();
    }
}
