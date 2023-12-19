// Amar & MC

package tests;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;
import java.util.*;

public class Group2_2_TestTeslaModelZ extends BCATestScenario {

    public int runTest() {
        // First Set of Constructors

        TeslaModelZ t1 = new TeslaModelZ(15, 1);
        TeslaModelZ t2 = new TeslaModelZ(10, 5);

        // Error for Constructor

        assertThrows(IllegalArgumentException.class, () -> {
            TeslaModelZ t3 = new TeslaModelZ(-1, 1);
        }, "Starting mileage cannot be negative.");

        // Second Set of Constructors

        TeslaModelZ t4 = new TeslaModelZ(2);
        TeslaModelZ t5 = new TeslaModelZ(0);
        TeslaModelZ t6 = new TeslaModelZ(-1);

        assertTrue(t4.getRemainingRange() == 0, "Starting mileage should equal 0.");

        // Methods of TeslaModelZ

        assertTrue(t1.getModelNum() == 1, "Model number should be 1.");
        assertTrue(t2.getModel().equals("Z5"), "String does not match; should be Z5.'");
        assertTrue(t2.toString().equals("Tesla Z5 (10.0 mi)"), "Model should be Z5 and mileage = 10.");

        // Methods of ElectricCar

        t1.drive(0);
        t1.drive(5);

        assertThrows(IllegalArgumentException.class, () -> {
            t1.drive(-5);
        }, "Cannot drive negative miles.");

        assertTrue(t4.getRemainingRange() == 340, "Remaining Range should be 340.");
        assertTrue(t5.getMaxRange() == 340, "Max range should be 340 miles.");

        t6.recharge();
        assertTrue(t6.getRemainingRange() == 340, "Remaining Range should be 340.");

        // Methods of Car

        assertThrows(IllegalArgumentException.class, () -> {
            t1.canDrive(-5);
        }, "Cannot drive negative miles.");

        assertTrue(t1.canDrive(336) == false, "Can't drive over the remaining range.");
        assertTrue(t1.canDrive(8) == true, "Can drive under the remaining range.");

        assertTrue(t6.getRemainingRange() == 0.0, "Mileage should be 0 miles.");
        assertTrue(t6.getMake().equals("Tesla"), "Make is Tesla.");

        assertTrue(t6.getModel().equals("Z-1"), "Model should be Z-1");

        List<Double> l1 = null;
        List<Double> l2 = new ArrayList<Double>();
        l2.add(-1.0);
        List<Double> l3 = new ArrayList<Double>();
        l3.add(43.0);
        l3.add(52.0);
        l3.add(39.0);

        assertThrows(NullPointerException.class, () -> {
            t6.roadTrip(l1);
        }, "List cannot reference to null");

        assertThrows(IllegalArgumentException.class, () -> {
            t6.roadTrip(l2);
        }, "Value cannot be negative for miles.");

        assertTrue(t6.roadTrip(l3) == 3, "They could drive for 3 days.");

        // Interface Checks

        t6.recharge();

        assertThrows(IllegalArgumentException.class, () -> {
            t6.driveAutonomously(-5);
        }, "Can't drive negative miles.");

        t6.driveAutonomously(5);
        assertTrue(t6.getRemainingRange() == 335, "Remaining range should be 335 miles.");

        t6.driveAutonomously(340);
        assertTrue(t6.getRemainingRange() == 0, "Remaining range should be 0 miles.");

        return getFailedCount();
    }
}
