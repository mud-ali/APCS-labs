package tests;

import java.util.ArrayList;
import java.util.Arrays;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group2_1_TestChevroletBird1_Constructors extends BCATestScenario {
    public int runTest() {

        assertThrows(IllegalArgumentException.class, () -> {
            new ChevroletBird(-20);
        },
                "Cannot have a negative startingMileage");

        ChevroletBird b1 = new ChevroletBird(20);
        b1.drive(20);

        assertEquals(b1.getRemainingRange(), 40, 0.1, "getRemainingRange should return 40");

        assertFalse(b1.checkWingsExtended(), "Wings should be retracted");

        assertEquals(b1.getRemainingRange(), 230, 0.1, "getRemainingRange should return 230");

        assertEquals(b1.getMaxRange(), 250, 0.1, "getMaxRange should always return 250");

        b1.recharge();

        assertEquals(b1.getRemainingRange(), 250, 0.1, "getRemainingRange should return 250");

        assertEquals(b1.getMake(), "Chevrolet", "The make should be Chevrolet");

        assertEquals(b1.getModel(), "Bird", "The model should be Bird");

        assertThrows(IllegalArgumentException.class, () -> {
            b1.drive(-5);
        }, "Cannot drive negative miles");

        assertThrows(IllegalArgumentException.class, () -> {
            b1.drive(251);
        }, "You do not have enough charge to drive 251 miles");

        b1.drive(250);

        assertFalse(b1.checkWingsExtended(), "wings should be retracted");

        assertEquals(b1.getRemainingRange(), 0, 0.1, "getRemainingRange() should be 0");

        assertEquals(b1.getRemainingRange(), 290, 0.1, "getRemainingRange() should now return 290");

        ChevroletBird b2 = new ChevroletBird();

        assertEquals(b2.getRemainingRange(), 0, 0.1,
                "getRemainingRange() should return 0 since it was just instantiated");

        assertEquals(b2.getRemainingRange(), 250, 0.1, "getRemainingRange() should return 250");

        assertThrows(IllegalArgumentException.class, () -> {
            b2.drive(-1);
        }, "Cannot drive negative miles");

        b2.drive(0);

        assertEquals(b2.getRemainingRange(), 250, 0.1, "getRemainingRange() should now return 250");

        b2.drive(250);

        assertEquals(b2.getRemainingRange(), 0, 0.1, "getRemainingRange() should now return 0");

        assertThrows(IllegalArgumentException.class, () -> {
            b2.drive(1);
        }, "You cannot drive 1 mile with the charge you have");

        assertEquals(b2.getMaxRange(), 250, 0.1, "getMaxRange() should return 250");

        b2.recharge();

        assertEquals(b2.getRemainingRange(), 250, 0.1, "getRemainingRange() should return 250");

        assertTrue(b2.canDrive(250), "car should be able to drive 250 miles");

        assertThrows(IllegalArgumentException.class, () -> {
            b2.canDrive(-6);
        }, "You cannot drive negative miles");

        assertFalse(b2.canDrive(251), "car cannot drive 251 miles");

        b2.drive(50);

        assertEquals(b2.getRemainingRange(), 200, 0.1, "getRemainingRange() should return 200");

        assertEquals(b2.getRemainingRange(), 300, 0.1, "getRemainingRange() should return 300");

        assertEquals(b2.toString(), "Chevrolet Bird (300.0 mi)", "toString should print: Chevrolet Bird (300.0 mi)");

        b2.recharge();

        ArrayList<Double> m1 = new ArrayList<>();
        Double[] m = { 4.5, 6.0, 7.0, -9.0 };
        m1.addAll(Arrays.asList(m));

        assertThrows(IllegalArgumentException.class, () -> {
            b2.roadTrip(m1);
        }, "You cannot drive negative miles");

        b2.recharge();

        ArrayList<Double> p1 = new ArrayList<>();
        Double[] p = { 55.0, 69.0, 140.0, 39.0 };
        p1.addAll(Arrays.asList(p));

        assertEquals(b2.roadTrip(p1), 2, 0.1, "roadTrip() should return 2");

        return getFailedCount();
    }

}
