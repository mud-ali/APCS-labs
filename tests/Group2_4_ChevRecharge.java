package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group2_4_ChevRecharge extends BCATestScenario {
    public int runTest() {
        ChevroletBird c1 = new ChevroletBird();
        
        c1.drive(250);
        assertEquals(c1.getRemainingRange(), 0, 0.1, "Remaining renge should be 0");
        c1.recharge();
        assertEquals(c1.getRemainingRange(), 250, 0.1, "Remaining range should be 250");

        c1.drive(100);
        assertEquals(c1.getRemainingRange(), 150, 0.1, "Remaining range should be 150");
        c1.recharge();
        assertEquals(c1.getRemainingRange(), 250, 0.1, "Remaining range should be 250 (2)");

        c1.recharge();
        assertEquals(c1.getRemainingRange(), 250, 0.1, "Remaining range should be 250 (3)");

        return getFailedCount();
    }
}