package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group7_4_ChevroletBird_Driving extends BCATestScenario{

    @Override
    public int runTest() {
        ChevroletBird c3 = new ChevroletBird();

        assertThrows(IllegalArgumentException.class, () -> {c3.drive(-1);}, "Driving mileage cannot be neg.");
        
        c3.drive(30.0);

        assertEquals(c3.getMileage(), 30.0, 1, "Car should have 30 mileage");

        c3.drive(200);

        assertEquals(c3.getMileage(), 230, .1, "Car should have 230 mileage");

        assertEquals(c3.getRemainingRange(), 20, .1, "Car should have 20 mileage left");

        assertFalse(c3.canDrive(21), "Should not be able to drive 21 miles");

        assertTrue(c3.canDrive(20), "Should be able to drive 20 miles");

        assertThrows(IllegalArgumentException.class, () -> {c3.drive(21);}, "Driving 21 miles is over the maximum mileage");

        return getFailedCount();
    }
    
}
