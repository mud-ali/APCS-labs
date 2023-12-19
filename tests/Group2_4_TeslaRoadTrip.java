package tests;

import vehicle.TeslaModelZ;
import bcatest.BCATestScenario;
import java.util.ArrayList;

public class Group2_4_TeslaRoadTrip extends BCATestScenario {
    public int runTest() {
        TeslaModelZ c1 = new TeslaModelZ(70);
        ArrayList<Double> list = new ArrayList<>();

        assertEquals(c1.roadTrip(list), 0, 0.1, "Days driven should be 0");

        list.add(0.0);
        assertEquals(c1.roadTrip(list), 1, 0.1, "Days driven should be 1");

        list.add(100.0);
        assertEquals(c1.roadTrip(list), 2, 0.1, "Days driven should be 2");

        list.add(140.0); // {0.0, 100.0, 140.0}
        assertEquals(c1.roadTrip(list), 3, 0.1, "Days driven should be 3");
        assertEquals(c1.getRemainingRange(), 340, 0.1, "Mileage should be 340");

        c1.recharge();
        list.add(101.0); // {0.0, 100.0, 140.0, 101.0}
        assertEquals(c1.roadTrip(list), 3, 0.1, "Days driven should be 3");

        c1.recharge();
        list.add(5.0); // {0.0, 100.0, 140.0, 101.0, 5.0}
        assertEquals(c1.roadTrip(list), 3, 0.1, "Days driven should be 3");

        ArrayList<Double> negativeList = new ArrayList<>();
        negativeList.add(-10.0);
        assertThrows(IllegalArgumentException.class, () -> {
            c1.roadTrip(negativeList);
        }, "Cannot drive negative miles");

        return getFailedCount();
    }
}