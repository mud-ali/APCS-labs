package tests;


import java.util.ArrayList;
import java.util.List;

import bcatest.BCATestScenario;
import vehicle.FordFrivolous;


public class Group2_6_TestFordFrivolous_Drive extends BCATestScenario {

    public int runTest() {
        FordFrivolous f = new FordFrivolous();
        FordFrivolous f2 = new FordFrivolous();
        FordFrivolous f3 = new FordFrivolous();
        FordFrivolous f4 = new FordFrivolous();

        assertThrows(IllegalArgumentException.class, () -> {
            f.drive(-1);
        }, "Negative mileage should throw an exception.");

        f.drive(10.1);

        assertEquals(f.getMileage(), 10.1, 0.1, "Mileage should be 10.1.");

        f.drive(0.0);

        assertEquals(f.getMileage(), 10.1, 0.1, "Mileage should be 10.1.");

        assertEquals(f.getMPG(), 23.6, 0.1, "MPG should be 23.6.");

        assertEquals(f.getFuelCapacity(), 20.0, 0.1, "Fuel capacity should be 20.");

        assertEquals(f2.getFuelLevel(), 20.0, 0.1, "Fuel level should be 20.");

        assertEquals(f2.getRemainingRange(), 472.0, 0.1, "Remaining range should be 472.");

        f2.drive(f2.getRemainingRange());

        assertEquals(f2.getFuelLevel(), 0.0, 0.1, "Fuel level should be 0.");

        assertEquals(f2.getMileage(), 472.0, 0.1, "Mileage should be 472.");

        assertThrows(IllegalArgumentException.class, () -> {
            f.drive(f.getRemainingRange() + 0.1);
        }, "Driving over the remaining range should throw an exception.");

        f2.refillTank();

        assertEquals(f2.getFuelLevel(), 20.0, 0.1, "The fuel level should be 20.");

        f.refillTank(f.getFuelCapacity() - f.getFuelLevel());

        assertEquals(f.getFuelLevel(), 20.0, 0.1, "The fuel level should be 20.");

        assertThrows(IllegalArgumentException.class, () -> {
            f.refillTank(f.getFuelCapacity() - f.getFuelLevel() + 0.1);
        }, "Overfilling the tank should throw an exception.");

        assertThrows(IllegalArgumentException.class, () -> {
            f.refillTank(-1);
        }, "Filling the tank with a negative fuel level amount should throw an exception.");

        f2.drive(f2.getRemainingRange()); // empties the tank

        f2.refillTank(f2.getFuelCapacity() - f2.getFuelLevel() - 1);

        assertEquals(f2.getFuelLevel(), 19.0, 0.1, "The fuel level should be 19.");

        // ROAD TRIP
        List<Double> list = new ArrayList<>();

        list.add(5.0);
        list.add(10.0);
        assertEquals(f3.roadTrip(list), 2, "Roadtrip is incorrect.");
        assertEquals(f3.getMileage(), 15.0, .1, "Roadtrip is incorrect.");

        list.clear();

        list.add(f3.getRemainingRange());
        list.add(.1);
        list.add(-1.0);
//        assertEquals(f3.roadTrip(list), 1, "Roadtrip is incorrect.");
//        assertEquals(f3.getMileage(), 472.0, .1, "Roadtrip is incorrect.");

        f3.refillTank();
        assertThrows(IllegalArgumentException.class, () -> {
            f3.roadTrip(list);
        },
                "Driving negative miles should throw an exception.");

        // Self Driving
        f3.refillTank();

        f3.driveAutonomously(0);
        assertEquals(f3.getMileage(), 15, 0.1, "Should be 15 before self drive.");

        assertEquals(f3.getMileage(), 15.0, 0.1, "Autonomously driving 0 miles should not increase mileage.");

        f3.driveAutonomously(f3.getRemainingRange() / 2);

        assertEquals(f3.getMileage(), 251, 0.1,
                "Autonomously driving the half of the range should increase the mileage by 236.");

        assertEquals(f3.getFuelLevel(), 0.0, 0.1,
                "Autonomously driving the half of the range should completely empty the fuel tank");

        f3.refillTank();

        f3.driveAutonomously(f3.getRemainingRange());

        assertEquals(f3.getMileage(), 487, 0.1,
                "Driving over the max range should be allowed and should stop at range = 0 ");

        assertThrows(IllegalArgumentException.class, () -> {
            f3.driveAutonomously(-1);
        },
                "Trying to drive a negative mileage should throw an exception.");

        // Flying
        assertTrue(f4.canFly(f4.getRemainingRange() / 3),
                "Flying should be allowed if the number of miles being flown is 1/3 of the driving range or less");

        assertFalse(f4.canFly(f4.getRemainingRange() / 3 + 1),
                "Flying should not be allowed if the number of miles being flown is greater than 1/3 of the driving range");

        assertThrows(IllegalArgumentException.class, () -> {
            f4.canFly(-1);
        },
                "Checking to fly a negative mileage should throw an exception.");

        assertThrows(IllegalArgumentException.class, () -> {
            f4.fly(-1);
        },
                "Flying a negative mileage should throw an exception.");

        assertThrows(IllegalArgumentException.class, () -> {
            f4.fly(f4.getRemainingRange() / 3 + 1);
        },
                "Flying a should throw an exception if the number of miles being flown is greater than 1/3 of the driving range.");

        f4.fly(f4.getRemainingRange() / 3);

        assertEquals(f4.getFuelLevel(), 0, 0.1, "Flying should decrease fuel level with 1/3 the mpg of driving.");

        assertEquals(f4.getMileage(), 0.0, 0.1, "Flying should not change mileage");

        return getFailedCount();
    }
}
