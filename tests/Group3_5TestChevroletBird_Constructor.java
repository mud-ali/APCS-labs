package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group3_5TestChevroletBird_Constructor extends BCATestScenario {

        @Override
        public int runTest() {
                ChevroletBird c1 = new ChevroletBird(100);
                assertEquals(c1.getRemainingRange(), 100, 0.1, "mileage should be 100");
                assertEquals(c1.getRemainingRange(), 250, 0.1,
                                "remaining range should be 250");
                assertFalse(c1.checkWingsExtended(),
                                "checkWingsExtended() should return false");

                assertEquals(c1.getMake(), "Chevrolet", "the make of c1 should be Chevrolet");
                assertEquals(c1.getModel(), "Bird", "the make of c1 should be Bird");

                ChevroletBird c2 = new ChevroletBird();
                assertEquals(c2.getRemainingRange(), 0, 0.1, "mileage should be 0");
                assertEquals(c2.getRemainingRange(), 250, 0.1,
                                "remaining range should be 250");
                assertFalse(c2.checkWingsExtended(),
                                "checkWingsExtended() should return false");

                assertThrows(IllegalArgumentException.class,
                                () -> {
                                        ChevroletBird c3 = new ChevroletBird(-1);
                                },
                                "ChevroletBird c3 = new ChevroletBird(-1) should throw IllegalArgumentException");

                return getFailedCount();
        }
}
