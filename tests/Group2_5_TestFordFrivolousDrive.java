//by Jaiden

package tests;

import bcatest.BCATestScenario;
import vehicle.FordFrivolous;
import java.util.Arrays;

public class Group2_5_TestFordFrivolousDrive extends BCATestScenario {

  // Given a day with more miles than a car could finish, I wasn't sure if the car
  // was supposed to attempt and stop halfway
  // thus changing its mileage/fuel level or if it would not attempt it at all, so
  // in this tester, I assumed it would not attempt it at all.

  // I also wasn't sure if a car would "attempt" to drive 0 miles on an empty tank
  // of gas, but in this tester, I assumed it would.
  public int runTest() {
    FordFrivolous F1 = new FordFrivolous();
    FordFrivolous F2 = new FordFrivolous(50);

    assertEquals(F1.getFuelCapacity(), 20, 0.1, "Fuel capacity should be 20 gallons.");
    assertEquals(F2.getFuelCapacity(), 20, 0.1, "Fuel capacity should be 20 gallons");

    F1.drive(0);
    assertEquals(F1.getRemainingRange(), 472, 0.1, "Remaning range is incorrect.");
    assertEquals(F1.getFuelLevel(), 20, 0.1, "Fuel level is incorrect.");
    assertEquals(F1.getRemainingRange(), 0, 0.1, "Mileage is incorrect, line 26.");

    assertThrows(IllegalArgumentException.class, () -> {
      F1.drive(-100);
    }, "Driving negative miles should throw an exception.");
    assertThrows(IllegalArgumentException.class, () -> {
      F1.drive(472.1);
    }, "Driving too many miles should throw an exception.");

    F1.drive(272);
    assertEquals(F1.getRemainingRange(), 200, 0.1, "Remaning range is incorrect.");
    assertEquals(F1.getFuelLevel(), 8.5, 0.1, "Fuel level is incorrect.");
    assertEquals(F1.getRemainingRange(), 272, 0.1, "Mileage is incorrect.");

    assertThrows(IllegalArgumentException.class, () -> {
      F2.drive(472.1);
    }, "Driving too many miles should throw an exception.");

    F2.drive(272);
    assertEquals(F2.getRemainingRange(), 200, 0.1, "Remaning range is incorrect.");
    assertEquals(F2.getFuelLevel(), 8.5, 0.1, "Fuel level is incorrect.");
    assertEquals(F2.getRemainingRange(), 322, 0.1, "Mileage is incorrect.");

    F1.refillTank();
    assertEquals(F1.getRemainingRange(), 472, 0.1, "Remaning range is incorrect.");
    assertEquals(F1.getFuelLevel(), 20, 0.1, "Fuel level is incorrect.");
    assertEquals(F1.getRemainingRange(), 272, 0.1, "Mileage is incorrect.");

    F2.refillTank();
    assertEquals(F2.getRemainingRange(), 472, 0.1, "Remaning range is incorrect.");
    assertEquals(F2.getFuelLevel(), 20, 0.1, "Fuel level is incorrect.");
    assertEquals(F2.getRemainingRange(), 322, 0.1, "Mileage is incorrect.");

    assertThrows(IllegalArgumentException.class, () -> {
      F1.refillTank(1.0);
    }, "Filling tank above capacity should throw an exception.");

    F1.drive(236);
    assertEquals(F1.getRemainingRange(), 236, 0.1, "Remaning range is incorrect.");
    assertEquals(F1.getFuelLevel(), 10, 0.1, "Fuel level is incorrect.");
    assertEquals(F1.getRemainingRange(), 508, 0.1, "Mileage is incorrect.");

    F2.drive(236);
    assertEquals(F2.getRemainingRange(), 236, 0.1, "Remaning range is incorrect.");
    assertEquals(F2.getFuelLevel(), 10, 0.1, "Fuel level is incorrect.");
    assertEquals(F2.getRemainingRange(), 558, 0.1, "Mileage is incorrect.");

    assertThrows(IllegalArgumentException.class, () -> {
      F1.refillTank(-2.0);
    }, "Filling tank with negative gallons should throw an exception.");
    assertThrows(IllegalArgumentException.class, () -> {
      F2.refillTank(-2.0);
    }, "Filling tank with negative gallons should throw an exception.");

    assertThrows(IllegalArgumentException.class, () -> {
      F1.refillTank(10.1);
    }, "Filling tank above capacity should throw an exception.");
    assertThrows(IllegalArgumentException.class, () -> {
      F2.refillTank(10.1);
    }, "Filling tank above capacity should throw an exception.");

    F1.refillTank(5);
    assertEquals(F1.getFuelLevel(), 15, 0.1, "Fuel level should be 15");

    F2.refillTank(5);
    assertEquals(F2.getFuelLevel(), 15, 0.1, "Fuel level should be 15");

    assertThrows(IllegalArgumentException.class, () -> {
      F1.drive(355);
    }, "Driving too many miles should throw an exception.");

    F1.drive(354);
    assertEquals(F1.getRemainingRange(), 0, 0.1, "Remaning range is incorrect.");
    assertEquals(F1.getFuelLevel(), 0, 0.1, "Fuel level is incorrect.");
    assertEquals(F1.getRemainingRange(), 862, 0.1, "Mileage is incorrect.");

    assertThrows(IllegalArgumentException.class, () -> {
      F2.drive(355);
    }, "Driving too many miles should throw an exception.");

    F2.drive(354);
    assertEquals(F2.getRemainingRange(), 0, 0.1, "Remaning range is incorrect.");
    assertEquals(F2.getFuelLevel(), 0, 0.1, "Fuel level is incorrect.");
    assertEquals(F2.getRemainingRange(), 912, 0.1, "Mileage is incorrect.");

    F1.refillTank();
    assertEquals(F1.getFuelLevel(), 20, 0.1, "Fuel level is incorrect.");

    F2.refillTank();
    assertEquals(F2.getFuelLevel(), 20, 0.1, "Fuel level is incorrect.");

    assertEquals(F1.roadTrip(Arrays.asList(473.0)), 0, 0.1, "Road trip is incorrect.");
    // assertEquals(F1.roadTrip(Arrays.asList(473.0, -1.0)), 0, 0.1, "Road trip is
    // incorrect.");
    assertThrows(IllegalArgumentException.class, () -> {
      F1.roadTrip(Arrays.asList(-1.0, 473.0));
    }, "Driving negative miles should throw an exception.");

    assertEquals(F1.roadTrip(Arrays.asList(472.0, 0.0, 0.0, 0.0, 1.0)), 4, 0.1, "Road trip is incorrect.");
    assertEquals(F1.getRemainingRange(), 0, 0.1, "Remaining range is incorrect");
    assertEquals(F1.getFuelLevel(), 0, 0.1, "Fuel level is incorrect");
    assertEquals(F1.getRemainingRange(), 1334, 0.1, "Mileage is incorrect");

    F1.refillTank();

    assertThrows(IllegalArgumentException.class, () -> {
      F1.roadTrip(Arrays.asList(472.0, 0.0, 0.0, 0.0, -1.0));
    }, "Driving negative miles should throw an exception.");

    F1.refillTank();

    assertEquals(F1.roadTrip(Arrays.asList(47.0, 92.0, 300.0)), 3, 0.1, "Road trip is incorrect.");
    assertEquals(F1.getRemainingRange(), 33, 0.1, "Remaining range is incorrect");
    assertEquals(F1.getFuelLevel(), 1.4, 0.1, "Fuel level is incorrect");
    assertEquals(F1.getRemainingRange(), 1773, 0.1, "Mileage is incorrect");

    assertEquals(F2.roadTrip(Arrays.asList(473.0)), 0, 0.1, "Road trip is incorrect.");
    // assertEquals(F2.roadTrip(Arrays.asList(473.0, -1.0)), 0, 0.1, "Road trip is
    // incorrect.");
    assertThrows(IllegalArgumentException.class, () -> {
      F2.roadTrip(Arrays.asList(-1.0, 473.0));
    }, "Driving negative miles should throw an exception.");

    assertEquals(F2.roadTrip(Arrays.asList(472.0, 0.0, 0.0, 0.0, 1.0)), 4, 0.1, "Road trip is incorrect.");
    assertEquals(F2.getRemainingRange(), 0, 0.1, "Remaining range is incorrect");
    assertEquals(F2.getFuelLevel(), 0, 0.1, "Fuel level is incorrect");
    assertEquals(F2.getRemainingRange(), 1384, 0.1, "Mileage is incorrect");

    F2.refillTank();

    assertThrows(IllegalArgumentException.class, () -> {
      F2.roadTrip(Arrays.asList(472.0, 0.0, 0.0, 0.0, -1.0));
    }, "Driving negative miles should throw an exception.");

    F2.refillTank();

    assertEquals(F2.roadTrip(Arrays.asList(47.0, 92.0, 300.0)), 3, 0.1, "Road trip is incorrect.");
    assertEquals(F2.getRemainingRange(), 33, 0.1, "Remaining range is incorrect");
    assertEquals(F2.getFuelLevel(), 1.4, 0.1, "Fuel level is incorrect");
    assertEquals(F2.getRemainingRange(), 1823, 0.1, "Mileage is incorrect");

    return getFailedCount();
  }

}
