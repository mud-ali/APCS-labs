package tests;

import bcatest.BCATestScenario;
import vehicle.HondaAccordian;

public class Group7_3_HondaAccordian_Constructor1 extends BCATestScenario {
    public int runTest() {
        HondaAccordian c1 = new HondaAccordian(2019);

        assertEquals(c1.getRemainingRange(), 0, .1, "Car should begin with 0 mileage");

        assertEquals(c1.toString(), "2019 Honda Accordian (0.0 mi)", "toString is correct");

        return getFailedCount();
    }

}
