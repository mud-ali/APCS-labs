package tests;

import java.util.ArrayList;
import java.util.Arrays;

import vehicle.HondaAccordian;
import bcatest.BCATestScenario;

public class Group2_1_TestHondaAccordian extends BCATestScenario {
    public int runTest() {
        HondaAccordian c1 = new HondaAccordian(2018);

        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(-1);
        }, "Driving negative mileage should throw an exception.");

        assertTrue(c1.canDrive(30), "Car can drive 30 miles (canDrive() should be true).");

        c1.drive(33.2);

        assertEquals(c1.getRemainingRange(), 33.2, 0.1, "getRemainingRange() should return 33.2");

        assertEquals(c1.getRemainingRange(), 13.5 * 33.2, 0.1, "getRemainingRange should return 448.2");

        c1.refillTank();

        assertEquals(c1.getRemainingRange(), 14.5 * 33.2, 0.1,
                "getRemainingRange should return 481.4 (Car should have a full tank");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.refillTank(-10);
        }, "Negative refill number should throw an exception");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.refillTank(15);
        }, "The car has a fuelCapacity of 14.5 gallons, so the method should throw an exception");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.canDrive(-10);
        }, "Negative canDrive should throw an exception");

        double miles = (c1.getFuelLevel() * 33.2) + 1;

        assertFalse(c1.canDrive(miles), "Should not be able to drive 1 mile higher than max");

        assertTrue(c1.canDrive(0), "canDrive 0 should return true");

        assertTrue(c1.canDrive(c1.getFuelLevel() * 33.2),
                "canDrive should return true (it can use up all the gas in the tank)");

        assertEquals(c1.toString(), "2018 Honda Accordian (" + c1.getRemainingRange() + " mi)",
                "Should return 2018 Honda Accordian and current mileage");

        assertEquals(c1.getMake(), "Honda", "the make is Honda");

        assertEquals(c1.getModel(), "Accordian", "the model is Accordian");

        assertEquals(c1.getMPG(), 33.2, 0.1, "getMPG() should return 33.2");

        assertEquals(c1.getFuelCapacity(), 14.5, 0.1, "getFuelLevel() should return 14.5");

        c1.refillTank();

        ArrayList<Double> m1 = new ArrayList<>();
        Double[] m = { 4.5, 6.0, 7.0, -9.0 };
        m1.addAll(Arrays.asList(m));

        assertThrows(IllegalArgumentException.class, () -> {
            c1.roadTrip(m1);
        }, "Cannot drive negative miles");

        c1.refillTank();

        ArrayList<Double> p1 = new ArrayList<>();
        Double[] p = { 140.0, 236.0, 94.0, 28.0 };
        p1.addAll(Arrays.asList(p));

        assertEquals(c1.roadTrip(p1), 3, "roadTrip should return number of days travelled, 3 days");

        c1.drive(c1.getFuelLevel() * 33.2);

        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(10);
        }, "Should throw an exception when you don't have enough gas");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.refillTank(-10);
        }, "A negative refill should throw an exception");

        c1.refillTank(10);

        assertEquals(c1.getFuelLevel(), 10, 0.1, "Should have 10 gallons in tank");

        HondaAccordian c2 = new HondaAccordian(481.4, 2019);

        assertEquals(c2.getRemainingRange(), 481.4, 0.1, "Car has already driven 481.4 miles");

        assertEquals(c2.getRemainingRange(), 481.4, 0.1, "Car should start with a full tank (481.4 miles)");

        return getFailedCount();
    }

}
