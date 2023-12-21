package tests;

import java.util.List;

import bcatest.BCATestScenario;
import vehicle.Car;
import vehicle.ChevroletBird;
import vehicle.FordFrivolous;
import vehicle.HondaAccordian;
import vehicle.TeslaModelZ;

public class TestRoadTrip extends BCATestScenario {

    public void testRoadTrip(Car c2) {
        List<Double> list = List.of(20.2, 50.0, 22.0);
        assertEquals(c2.roadTrip(list), 3, .1, "Unexpected # of days in road trip");

        assertEquals(c2.getOdometerMiles(), 10092.2, .1, "Unexpected odometer value after road trip");

        List<Double> list2 = List.of(20.2, -20.0, 20.0);
        assertThrows(IllegalArgumentException.class, () -> {
            c2.roadTrip(list2);
        }, "Exception not thrown when attempting to drive negative amount in road trip");

        assertEquals(c2.getOdometerMiles(), 10092.2, .1, "Unexpected mileage amount after recovering from exception");

        // Drive so that there are only 26 remaining miles
        c2.drive(c2.getRemainingRange() - 26);
        assertEquals(c2.getRemainingRange(), 26, .1, "Remaining range should be 15.");
        double miles = c2.getOdometerMiles();

        // Go on a road trip that will get cut short due to lack of fuel.
        List<Double> list3 = List.of(5.0, 20.0, 10.0);
        assertEquals(c2.roadTrip(list3), 2, .1,
                "Unexpected # of days in road trip. Car should have only driven 2 days.");

        assertEquals(c2.getOdometerMiles(), miles + 25, .1,
                "Odometer miles following road trip should have increased by 25.");
    }

    public int runTest() {
        testRoadTrip(new HondaAccordian(10000, 2018));
        testRoadTrip(new TeslaModelZ(10000, 5));
        testRoadTrip(new ChevroletBird(10000));
        testRoadTrip(new FordFrivolous(10000));

        return getFailedCount();
    }
}
