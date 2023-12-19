package tests;
import vehicle.TeslaModelZ;
import bcatest.BCATestScenario;

public class Group3_4_TestTeslaModelZb extends BCATestScenario {
    @Override
    public int runTest() {

    TeslaModelZ t3 = new TeslaModelZ(70);
    assertThrows(IllegalArgumentException.class, () -> {t3.driveAutonomously(-1);}, "neg autonomous -- supposed to throw IllegalArgumentException");
    //run drive 0 verify no eception, mileage 0
    t3.driveAutonomously(30);
    assertEquals(t3.getMileage(), 30, 0.1, "Wrong Mileage");
    t3.driveAutonomously(300);
    assertEquals(t3.getMileage(), 330, 0.1, "Wrong Mileage");
    assertEquals(t3.getRemainingRange(), 10, 0.1, "Wrong Remaining Range");
    t3.driveAutonomously(9);
    assertEquals(t3.getMileage(), 339, 0.1, "Wrong Mileage");
    t3.driveAutonomously(2);
    //run drive autonomouly 1 and verify no exception is thrown

    return getFailedCount();

    }
}
