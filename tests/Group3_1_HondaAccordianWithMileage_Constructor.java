package tests;

// Ved, Sam, & Artem

import java.util.ArrayList;
import bcatest.BCATestScenario;
import vehicle.HondaAccordian;

public class Group3_1_HondaAccordianWithMileage_Constructor extends BCATestScenario{

    @Override
    public int runTest() {
        HondaAccordian c1 = new HondaAccordian(500, 2018);
        assertEquals(c1.getMileage(), 500, 0.1, "Mileage should equal 500");
        assertEquals(c1.getFuelCapacity(), 14.5, 0.1, "Fuel capacity should equal 14.5");
        assertEquals(c1.getMPG(), 33.2, 0.1, "MPG should equal 33.2");
        assertEquals(c1.getRemainingRange(), 481.4, 0.1, "Remaining Range should equal 481.4");
        assertEquals(c1.toString(), "2018 Honda Accordian (500.0 mi)", "toString() should print '2018 Honda Accordian (500.0 mi)'");
        assertThrows(IllegalArgumentException.class, () -> {c1.drive(500); }, "Driving 500 miles should throw an IllegalArgumentException");
        assertThrows(IllegalArgumentException.class, () -> {c1.drive(-10); }, "Driving -10 miles should throw an IllegalArgumentException");
        c1.drive(50);
        assertEquals(c1.getMileage(), 550.0, 0.1, "Mileage should be 550.0 after driving 50 miles");
        assertEquals(c1.getRemainingRange(), 431.4, 0.1, "Remaining range should be 431.4 after driving 50 miles");
        c1.refillTank();
        assertEquals(c1.getRemainingRange(), 481.4, 0.1, "Remaining range should be 481.4 after refilling tank");
        assertThrows(IllegalArgumentException.class, () -> {c1.refillTank(-10); }, "Refilling tank with -10 gallons should throw an IllegalArgumentException");
        assertThrows(IllegalArgumentException.class, () -> {c1.refillTank(10); }, "Refilling tank beyond capacity should throw an IllegalArgumentException");
        c1.drive(66.4);
        assertEquals(c1.getFuelLevel(), 12.5, 0.1, "Fuel level should equal 12.5");
        c1.refillTank(2);
        assertEquals(c1.getRemainingRange(), 481.4, 0.1, "Refilling tank to full should have a remaining range of 481.4");
 //       assertFalse(c1.canDrive(-10), "Cannot drive negative miles");
        assertFalse(c1.canDrive(500), "Cannot drive beyond max miles");
        assertEquals(c1.getMake(), "Honda", "Make is Honda");
        assertEquals(c1.getModel(), "Accordian", "Model is Accordian");
        
        ArrayList<Double> one = new ArrayList<Double>();
        one.add(300.0);
        one.add(300.0);
        assertEquals(c1.roadTrip(one), 1, 0.1, "Road Trip counts incorrectly");

        ArrayList<Double> error = new ArrayList<Double>();
        error.add(-10.0);
        assertThrows(IllegalArgumentException.class, () -> { c1.roadTrip(error); }, "Road Trip should throw exception at negative distances");
        return getFailedCount();
    }

}
