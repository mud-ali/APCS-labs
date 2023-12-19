package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group3_5TestChevroletBird_canDrive extends BCATestScenario {

    @Override
    public int runTest() {
        ChevroletBird chev = new ChevroletBird();

        assertThrows(IllegalArgumentException.class, () -> {
            chev.canDrive(-1);
        },
                "chevie.canDrive(-1) should throw IllegalArgumentException");

        assertTrue(chev.canDrive(50), "chev.canDrive(50) should be true");

        chev.drive(50);
        assertEquals(chev.getRemainingRange(), 200, 0.1, "remaining range should be 200");

        assertFalse(chev.canDrive(201), "chev.canDrive(201) should be false");
        assertTrue(chev.canDrive(200), "chev.canDrive(200) should be true");

        chev.drive(200);
        assertFalse(chev.canDrive(1), "chev.canDrive(1) should be false");

        return getFailedCount();
    }
}