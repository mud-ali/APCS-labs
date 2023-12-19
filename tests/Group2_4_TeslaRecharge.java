package tests;

import vehicle.TeslaModelZ;
import bcatest.BCATestScenario;

public class Group2_4_TeslaRecharge extends BCATestScenario{
    public int runTest(){
        TeslaModelZ c1 = new TeslaModelZ(70);

        c1.drive(340);
        assertEquals(c1.getRemainingRange(), 0, 0.1, "Remaining range should be 0 after driving max miles");

        c1.recharge();
        assertEquals(c1.getRemainingRange(), 340, 0.1, "Remaining range should be 340 after recharging");

        c1.drive(100);
        assertEquals(c1.getRemainingRange(), 240, 0.1, "Remaining range should be 240");

        c1.recharge();
        assertEquals(c1.getRemainingRange(), 340, 0.1, "Remaining range should be 340 after recharging");
        
        return getFailedCount();
    }
}
