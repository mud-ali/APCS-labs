package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group3_5TestChevroletBird_checkWingsExtended extends BCATestScenario {
        @Override
        public int runTest() {
                ChevroletBird chevie = new ChevroletBird();

                assertEquals(chevie.getRemainingRange(), 0, 0.1, "mileage should equal 0");
                assertFalse(chevie.checkWingsExtended(), "checkWingsExtended should be false");

                assertThrows(IllegalArgumentException.class, () -> {
                        chevie.canFly(-1);
                },
                                "chevie.canFly(-1) should throw IllegalArgumentException");

                assertTrue(chevie.canFly(0), "chevie.canfly(0) should be true");

                assertThrows(IllegalArgumentException.class, () -> {
                        chevie.fly(-1);
                },
                                "chev.fly(-1) should throw IllegalArgumentException");

                chevie.fly(30);
                assertEquals(chevie.getRemainingRange(), 220, 0.1,
                                "chevie.getRemainingRange should be 220");
                assertEquals(chevie.getRemainingRange(), 0, 0.1, "chevie.getRemainingRange should be 0");

                assertTrue(chevie.checkWingsExtended(),
                                "checkWingsExtended should be true");

                chevie.drive(0);
                assertFalse(chevie.checkWingsExtended(),
                                "checkWingsExtended should be false");

                return getFailedCount();
        }

}
