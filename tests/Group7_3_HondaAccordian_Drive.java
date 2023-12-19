package tests;
import bcatest.BCATestScenario;
import vehicle.HondaAccordian;

public class  Group7_3_HondaAccordian_Drive extends BCATestScenario {
    public int runTest() {
        HondaAccordian c = new HondaAccordian(2018);

        assertThrows(IllegalArgumentException.class, () -> {c.drive(-1);}, "Driving mileage cannot be neg.");

        c.drive(200);

        assertTrue(c.canDrive(50), "Should be able to drive 50");

        return getFailedCount();
    }
}

    






