package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group3_5TestChevroletBird_Flying extends BCATestScenario {

    @Override
    public int runTest() {
        ChevroletBird chev = new ChevroletBird();

        assertThrows(IllegalArgumentException.class, () -> {
            chev.canFly(-1);
        },
                "chev.drive(-1) should throw IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> {
            chev.fly(-1);
        },
                "chev.drive(-1) should throw IllegalArgumentException");

        assertTrue(chev.canFly(50), "chev.canfly(50) should be true");

        chev.fly(50);
        assertEquals(chev.getMileage(), 0, 0.1, "mileage should be 0");
        assertEquals(chev.getRemainingRange(), 200, 0.1, "remianing range should be 200");
        assertTrue(chev.checkWingsExtended(), "checkWingsExtended should be true");

        assertFalse(chev.canFly(201), "chev.canFly(201) should be false");

        ChevroletBird chevie = new ChevroletBird(500);

        assertTrue(chevie.canFly(30), "chevie.canfly(30) should be true");

        chevie.fly(30);
        assertEquals(chevie.getMileage(), 500, 0.1, "mileage should be 500");
        assertEquals(chevie.getRemainingRange(), 220, 0.1, "remianing range should be 220");
        assertTrue(chevie.checkWingsExtended(), "checkWingsExtended should be true");

        assertFalse(chevie.canFly(221), "chev.canFly(221) should be false");

        return getFailedCount();
    }

}
