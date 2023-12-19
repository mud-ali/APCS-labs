package tests;
import bcatest.BCATestScenario;
import vehicle.HondaAccordian;

public class Group2_3_TestHondaAccordianCreating extends BCATestScenario {
    public int runTest() {
            
            assertThrows(IllegalArgumentException.class, () -> {HondaAccordian a = new HondaAccordian(-1, 2000);}, 
            "Negative starting mileage should throw an exception.");
            
            HondaAccordian b = new HondaAccordian(2000);
            assertEquals(b.getMileage(), 0, 0.1, "Mileage should be zero.");

            HondaAccordian c = new HondaAccordian(20, 2000);
            assertEquals(c.getMileage(), 20, 0.1, "Mileage should be 20.0");
            assertEquals(b.getModel(), "Accordian", "Model should be Accordian");
            assertEquals(c.getMPG(), 33.2, 0.1, "MPG should be 33.2.");
            assertEquals(c.getFuelCapacity(),14.5, 0.1,"Fuel capacity should be 14.5");
            assertEquals(c.toString(), "2000 Honda Accordian (20.0 mi)", "toString does not match");

            return getFailedCount();
    }
}
