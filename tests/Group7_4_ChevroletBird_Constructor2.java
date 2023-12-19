package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group7_4_ChevroletBird_Constructor2 extends BCATestScenario {

    @Override
    public int runTest() {
        ChevroletBird c2 = new ChevroletBird(50.0);

        assertEquals(c2.getRemainingRange(), 50.0, .1, "Car should begin with 50.0 mileage.");

        assertEquals(c2.getRemainingRange(), 250.0, .1, "Car's remaining range should be 200.0.");

        assertEquals(c2.getMaxRange(), 250.0, .1, "Car's max range should be 250.0.");

        assertFalse(c2.checkWingsExtended(), "Car's wings should be retracted.");

        assertEquals(c2.toString(), "Chevrolet Bird (50.0 mi)", "toString is incorrect");

        return getFailedCount();
    }

}
