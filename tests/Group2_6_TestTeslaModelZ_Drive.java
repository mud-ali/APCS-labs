package tests;

import java.util.ArrayList;
import java.util.List;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;

public class Group2_6_TestTeslaModelZ_Drive extends BCATestScenario {
    public int runTest() {
        TeslaModelZ t = new TeslaModelZ(1);
        TeslaModelZ t2 = new TeslaModelZ(0.0, 4);

        assertThrows(IllegalArgumentException.class, () -> {
            t.drive(-1);
        },
                "Driving negative mileage should throw an exception.");

        t.drive(10.1);
        assertEquals(t.getMileage(), 10.1, .1, "Mileage is incorrect.");

        t.drive(0);
        assertEquals(t.getMileage(), 10.1, .1, "Mileage is incorrect.");

        assertEquals(t.getMaxRange(), 340, .1, "Max range is incorrect.");

        assertEquals(t.getRemainingRange(), 329.9, .1, "Remaining range is incorrect.");

        t.drive(t.getRemainingRange());
        assertEquals(t.getRemainingRange(), 0, .1, "Remaining range is incorrect.");
        assertEquals(t.getMileage(), 340, .1, "Mileage is incorrect.");

        assertThrows(IllegalArgumentException.class, () -> {
            t.drive(1);
        },
                "Driving more than remaining range should throw an exception.");

        t.recharge();

        assertEquals(t.getRemainingRange(), 340., .1, "Recharging is incorrect.");

        assertTrue(t.canDrive(340), "Driving max range should be allowed.");

        assertFalse(t.canDrive(341), "Driving more than max range should not be allowed.");

        assertTrue(t.canDrive(0), "Driving 0 miles should be allowed.");

//        assertFalse(t.canDrive(-1), "Driving negative miles should not be allowed.");

        // drive autonomously below

        t2.recharge();

        t2.driveAutonomously(0);

        assertEquals(t2.getMileage(), 0.0, 0.1, "Driving 0 miles should keep the mileage at 0.");

        t2.driveAutonomously(t.getRemainingRange());

        assertEquals(t2.getMileage(), 340.0, 0.1, "Driving 340 miles should increase the mileage to 340.");

        assertEquals(t2.getRemainingRange(), 0.0, 0.1,
                "Autonomously driving the full range should completely empty the battery");

        t2.driveAutonomously(t.getRemainingRange() + 1);

        assertEquals(t2.getMileage(), 340.0, 0.1,
                "Driving over the max range should be allowed and should stop at range = 0 ");

        assertThrows(IllegalArgumentException.class, () -> {
            t2.driveAutonomously(-1);
        },
                "Trying to drive a negative mileage should throw an exception.");

        // roadtrip below

        List<Double> list = new ArrayList<>();
        list.add(5.0);
        list.add(10.0);
        assertEquals(t.roadTrip(list), 2, "Roadtrip is incorrect.");
        assertEquals(t.getMileage(), 355.0, .1, "Roadtrip is incorrect.");

        list.clear();
        list.add(t.getRemainingRange());
        list.add(.1);
        assertEquals(t.roadTrip(list), 1, "Roadtrip is incorrect.");
        assertEquals(t.getMileage(), 680.0, .1, "Roadtrip is incorrect.");

        t.recharge();
        list.add(-1.0);
        assertThrows(IllegalArgumentException.class, () -> {
            t.roadTrip(list);
        },
                "Driving negative miles should throw an exception.");

        return getFailedCount();
    }

}