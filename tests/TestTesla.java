package tests;

import bcatest.BCATestScenario;
import vehicle.TeslaModelZ;

public class TestTesla extends BCATestScenario {

        @Override
        public int runTest() {
                final TeslaModelZ c1 = new TeslaModelZ(50);

                assertThrows(IllegalArgumentException.class, () -> {
                        c1.drive(-1);
                }, "Driving odometer cannot be negative.");

                assertTrue(c1.canDrive(30), "canDrive should be true");
                c1.drive(30);
                assertEquals(c1.getOdometerMiles(), 30, .1, "Odometer should be 30 after first drive.");

                c1.drive(200.5);
                assertEquals(c1.getOdometerMiles(), 230.5, .1, "Odometer should be 230 after second drive.");

                assertEquals(c1.toString(), "Tesla Z50 (230.5 mi)", "toString does not match");

                assertEquals(c1.getRemainingRange(), c1.getMaxRange() - 230.5, .1,
                                "Remaining range of car not correct after driving three times.");

                assertFalse(c1.canDrive(111), "Driving 111 should fail.");
                assertTrue(c1.canDrive(109), "Driving 109 should succeed.");

                c1.drive(109.5);
                assertEquals(c1.getOdometerMiles(), 340, .1, "Odometer should be 340 after third drive.");

                assertThrows(IllegalArgumentException.class, () -> {
                        c1.drive(5);
                }, "Driving beyond empty should fail.");

                c1.recharge();
                c1.drive(c1.getMaxRange());
                assertEquals(c1.getOdometerMiles(), 680, .1, "Odometer should be 680.");

                //////////////////////////
                // Autonomous driving test
                //
                TeslaModelZ c2 = new TeslaModelZ(1000, 50);
                assertThrows(IllegalArgumentException.class, () -> {
                        c2.driveAutonomously(-0.1);
                }, "Driving odometer cannot be negative.");

                c2.driveAutonomously(0);
                assertEquals(c2.getOdometerMiles(), 1000, .1, "Odometer should be 1000 after first drive.");

                c2.driveAutonomously(30);
                assertEquals(c2.getOdometerMiles(), 1030, .1, "Odometer should be 1030 after second drive.");

                c2.driveAutonomously(200);
                assertEquals(c2.getOdometerMiles(), 1230, .1, "Odometer should be 1230 after third drive.");

                assertEquals(c2.getRemainingRange(), c2.getMaxRange() - 230, .1,
                                "Remaining range of car not correct after driving three times.");

                c2.recharge();
                assertEquals(c2.getRemainingRange(), c2.getMaxRange(), .1,
                                "Remaining range of car not correct after recharging.");

                c2.driveAutonomously(500);
                assertEquals(c2.getOdometerMiles(), 1570, .1, "Odometer should be 570 after fourth drive.");

                assertEquals(c2.getRemainingRange(), 0, .1,
                                "Car should be empty after trying to drive too far.");

                c2.driveAutonomously(341);
                assertEquals(c2.getOdometerMiles(), 1570, .1, "Car is empty, no change in odometer.");

                return 0;
        }

}
