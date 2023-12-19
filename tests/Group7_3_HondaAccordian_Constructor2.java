package tests;

import bcatest.BCATestScenario;
import vehicle.HondaAccordian;

public class Group7_3_HondaAccordian_Constructor2 extends BCATestScenario {
    public int runTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            HondaAccordian c1 = new HondaAccordian(-1, 2018);
        }, "Driving mileage cannot be neg.");
        HondaAccordian c2 = new HondaAccordian(10, 2019);
        assertEquals(c2.getRemainingRange(), 10, .1, "Car should begin with 10 mileage");
        assertEquals(c2.getMake(), "Honda", "should have created a honda");
        assertEquals(c2.getModel(), "Accordian", "should have created an accordian");
        assertEquals(c2.getFuelCapacity(), 14.5, 0.1, "Car should have 14.5 gallons of fuel");
        assertEquals(c2.getFuelLevel(), 14.5, 0.1, "Fuel level should match fuel capacity when car is first created");
        assertEquals(c2.getMPG(), 33.2, 0.1, "mpg of honda accordian should be 22.3");
        assertEquals(c2.getRemainingRange(), 481.4, 0.1, "range of accordian on full tank should be 481.4 mi");
        assertEquals(c2.toString(), "2019 Honda Accordian (10.0 mi)", "toString is incorrect");

        return getFailedCount();
    }

}
