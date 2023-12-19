package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group3_5TestChevroletBird_Drive extends BCATestScenario {

    @Override
    public int runTest() {
        ChevroletBird chev = new ChevroletBird();
        assertFalse(chev.checkWingsExtended(), "checkWingsExtended should return false" +
                "before driving");
        assertThrows(IllegalArgumentException.class, () -> {
            chev.drive(-1);
        },
                "chev.drive(-1) should throw IllegalArgumentException");
        assertTrue(chev.canDrive(30), "canDrive(30) should be true");

        chev.drive(10);

        assertEquals(chev.getMileage(), 10, 0.1, "mileage should be 10");

        chev.drive(200);

        assertEquals(chev.getMileage(), 210, 0.1, "mileage should be 210");
        assertEquals(chev.getRemainingRange(), 40, 0.1, "remaining range should be 40");

        chev.drive(40);

        assertEquals(chev.getMileage(), 250, 0.1, "mileage should be 250");
        assertThrows(IllegalArgumentException.class, () -> {
            chev.drive(0.1);
        },
                "chev.drive(0.1) should throw IllegalArgumentException");

        return getFailedCount();
    }

}