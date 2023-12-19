package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group3_5TestChevroletBird_getRemainingRange extends BCATestScenario {

    @Override
    public int runTest() {
        ChevroletBird chev = new ChevroletBird();
        assertEquals(chev.getMaxRange(), 250, 0.1, "max range for chev should be 250");
        assertEquals(chev.getRemainingRange(), 250, 0.1, "getRemainingRange should be 250");

        chev.drive(50);
        assertEquals(chev.getRemainingRange(), 200, 0.1, "remaining range should be 200");

        chev.recharge();
        assertEquals(chev.getRemainingRange(), 250, 0.1, "remaining range should be 250");

//        chev.decreaseCharge(30);
//        assertEquals(chev.getRemainingRange(), 220, 0.1, "remaining range should be 220");

        return getFailedCount();
    }

}
