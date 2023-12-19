package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group2_4_ChevZeroMileConstructor extends BCATestScenario {
    public int runTest() {
        ChevroletBird c1 = new ChevroletBird();

        assertEquals(c1.getRemainingRange(), 0, 0.1, "Default mileage should be 0");
        assertEquals(c1.getRemainingRange(), 250, 0.1, "Default remaining range should be 250");
        assertEquals(c1.getMaxRange(), 250, 0.1, "Miles on max charge should be 250");

        assertEquals(c1.toString(), "Chevrolet Bird (0.0 mi)", "toString does not match");

        assertFalse(c1.checkWingsExtended(), "wings should not be extended. False expected");
        assertEquals(c1.getMake(), "Chevrolet", "Makes do not match");
        assertEquals(c1.getModel(), "Bird", "Models do not match");

        return getFailedCount();
    }
}