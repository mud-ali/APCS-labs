package tests;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;

public class Group3_2_Tesla2_Driving extends BCATestScenario {

    @Override
    public int runTest() {
        TeslaModelZ c1 = new TeslaModelZ(70);

        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(-1);
        },
                "Driving negative mileage should throw IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(341);
        },
                "Too high of a mileage should throw IllegalArgumentException");

        assertTrue(c1.canDrive(0), "canDrive(0) should be true");

        assertTrue(c1.canDrive(20), "canDrive(20) should be true");
        c1.drive(20);
        assertEquals(c1.getRemainingRange(), 20, 0.01, "Mileage should be 20");

        assertTrue(c1.canDrive(200.2), "canDrive(200.2) should be true");
        c1.drive(200.2);
        assertEquals(c1.getRemainingRange(), 220.2, 0.01, "Mileage should be 220.2");

        assertEquals(c1.getRemainingRange(), 119.8, 0.01, "Remaining range should be 119.8");

        assertFalse(c1.canDrive(120), "canDrive(120) should be false");

        assertTrue(c1.canDrive(119), "canDrive(119) should be true");
        c1.drive(119);
        assertEquals(c1.getRemainingRange(), 339.2, 0.01, "Mileage should be 339.2");

        assertThrows(IllegalArgumentException.class, () -> {
            c1.drive(0.9);
        },
                "Too high of a mileage should throw IllegalArgumentException");

        assertTrue(c1.canDrive(0.2), "canDrive(0.2) should be true");

        c1.recharge();
        assertEquals(c1.getRemainingRange(), 340, 0.01, "Remaining range should be 340");

        /*
         * c1.decreaseCharge(20);
         * assertEquals(c1.getRemainingRange(), 320, 0.01,
         * "Remaining range should be 320");
         * 
         * assertThrows(IllegalArgumentException.class, () -> {c1.addMileage(-10);},
         * "Adding a negative mileage should throw an exception");
         * 
         * c1.addMileage(10);
         * assertEquals(c1.getRemainingRange(), 349.2, 0.01, "Mileage should be 349.2");
         */
        return getFailedCount();
    }

}
