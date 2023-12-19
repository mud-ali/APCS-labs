package tests;
import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group7_4_ChevroletBird_Constructor1 extends BCATestScenario{

    @Override
    public int runTest() {
        ChevroletBird c1 = new ChevroletBird();

        assertEquals(c1.getMileage(), 0, .1, "Car should begin with 0 mileage.");

        assertEquals(c1.getRemainingRange(), 250, .1, "Car's remaining range should be 250.");

        assertEquals(c1.getMaxRange(), 250, .1, "Car's max range should be 250.");

        assertFalse(c1.checkWingsExtended(), "Car's wings should be retracted.");

        assertEquals(c1.toString(), "Chevrolet Bird (0.0 mi)", "toString is incorrect");

        return getFailedCount();
    }

}