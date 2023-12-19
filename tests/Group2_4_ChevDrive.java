package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group2_4_ChevDrive extends BCATestScenario {
    public int runTest() {
        ChevroletBird c1 = new ChevroletBird();
        
        assertThrows(IllegalArgumentException.class, () -> {c1.drive(-1);}, 
        "Driving negative mieage should throw an exception.");

        assertTrue(c1.canDrive(0), "canDrive should be true");
        c1.drive(0);

        assertTrue(c1.canDrive(50), "canDrive should be true");
        c1.drive(50);
        c1.drive(110);

        assertEquals(c1.getRemainingRange(), 90, 0.1, "Remaining range should be 90");

        assertFalse(c1.canDrive(91), "canDrive should be false");
        assertTrue(c1.canDrive(90), "canDrive should be true");
        c1.drive(85);

        assertThrows(IllegalArgumentException.class, () -> {c1.drive(5.1);},
        "Driving more than the remaining range should throw an exception.");
        c1.drive(5);

        assertEquals(c1.getRemainingRange(), 0, 0.1, "Remaining reange should be 0");
        
        return getFailedCount();
    }
}