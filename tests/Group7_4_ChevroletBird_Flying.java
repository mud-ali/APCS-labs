package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group7_4_ChevroletBird_Flying extends BCATestScenario {

    @Override
    public int runTest() {

        ChevroletBird c4 = new ChevroletBird();

        assertThrows(IllegalArgumentException.class, () -> {
            c4.fly(-1);
        }, "Flying mileage can not be negative");

        c4.fly(30);

        assertEquals(c4.getRemainingRange(), 0, .1, "Car mileage should remain 0");

        c4.fly(200);

        assertEquals(c4.getRemainingRange(), 0, .1, "Car mileage should remain 0");

        assertEquals(c4.getRemainingRange(), 20, .1, "Car should have 20 mileage left");

        assertFalse(c4.canFly(21), "Car should not be able to fly with 21 mileage");

        assertTrue(c4.canFly(20), "Car should be able to fly with 20 mileage");

        assertThrows(IllegalArgumentException.class, () -> {
            c4.fly(21);
        }, "21 mileage goes over the Car's maximum mileage");

        return getFailedCount();
    }

}
