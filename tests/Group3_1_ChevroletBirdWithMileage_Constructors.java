package tests;

// Ved, Sam, & Artem

import java.util.ArrayList;
import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group3_1_ChevroletBirdWithMileage_Constructors extends BCATestScenario {

    @Override
    public int runTest() {
        ChevroletBird c1 = new ChevroletBird(2000.5);

        assertEquals(c1.getMake(), "Chevrolet", "Returns incorrect make");
        assertEquals(c1.getModel(), "Bird", "Returns incorrect model");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.canDrive(-10);
        }, "Cannot drive negative miles");
        assertFalse(c1.canDrive(260), "Cannot drive miles that are larger than max range");

        assertEquals(c1.getRemainingRange(), 2000.5, 0.1, "Initial mileage should be 2000.5"); // 0.1 is delta change
        assertFalse(c1.checkWingsExtended(), "Initial wings should not be extended, should return false");
        assertEquals(c1.getMaxRange(), 250, 0.1, "Max range should be limited to 250 miles");
        assertEquals(c1.getRemainingRange(), c1.getMaxRange(), 0.1, "Initial range should be equal to maximum range");
        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(1000000000);
        }, "Should not be able to drive extreme amounts of miles, limit the max");
        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(-1);
        }, "Driving negative mileage should throw illegalArgumentException");

        c1.drive(10);
        assertEquals(c1.getRemainingRange(), c1.getMaxRange() - 10.0, 0.1,
                "Remaining range should be updating after driving");
        assertEquals(c1.getRemainingRange(), 2000.5 + 10, 0.1, "Mileage driven should be updated after driving");
        c1.recharge();
        assertEquals(c1.getRemainingRange(), c1.getMaxRange(), 0.1,
                "After recharge, remaining range should be equal to maximum range");

        assertEquals(c1.toString(), "Chevrolet Bird (2010.5 mi)", "toString() does not return proper print");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.canFly(-250);
        }, "cannot fly negative miles");
        assertTrue(c1.canFly(250), "Must be able to fly positive miles");
        c1.fly(20);
        assertEquals(c1.getRemainingRange(), 2000.5 + 10.0, 0.1, "flying must not count toward mileage");
        assertTrue(c1.checkWingsExtended(), "wings must be still open after flying");

        c1.drive(20);
        assertFalse(c1.checkWingsExtended(), "wings must be closed after driving");

        ArrayList<Double> one = new ArrayList<Double>();
        one.add(150.0);
        one.add(200.0);
        assertEquals(c1.roadTrip(one), 1, 0.1, "Road Trip counts incorrectly");

        ArrayList<Double> error = new ArrayList<Double>();
        error.add(-10.0);
        assertThrows(IllegalArgumentException.class, () -> {
            c1.roadTrip(error);
        }, "Road Trip should throw exception at negative distances");

        return getFailedCount(); // use this for all the tests
    }

}
