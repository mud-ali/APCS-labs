package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group3_5TestChevroletBird_getMileage extends BCATestScenario {

    @Override
    public int runTest() {
        ChevroletBird chev = new ChevroletBird();

        assertEquals(chev.getRemainingRange(), 0, 0.1, "getRemainingRange should be 0");

        chev.drive(100);
        assertEquals(chev.getRemainingRange(), 100, 0.1, "getRemainingRange should be 100");

        chev.drive(50);
        assertEquals(chev.getRemainingRange(), 150, 0.1, "getRemainingRange should be 150");

        ChevroletBird chevie = new ChevroletBird(250.0);

        assertEquals(chevie.getRemainingRange(), 250, 0.1, "getRemainingRange should be 250");

        chevie.drive(100);
        assertEquals(chevie.getRemainingRange(), 350, 0.1, "getRemainingRange should be 350");

        return getFailedCount();
    }
}
