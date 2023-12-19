//by Jaiden

package tests;

import bcatest.BCATestScenario;
import vehicle.FordFrivolous;

public class Group2_5_TestFordFrivolousConstructors extends BCATestScenario {

  public int runTest() {
    FordFrivolous F1 = new FordFrivolous(0);
    FordFrivolous F2 = new FordFrivolous(50);
    assertThrows(IllegalArgumentException.class, () -> {
      FordFrivolous F3 = new FordFrivolous(-50);
    }, "Negative starting mileage should throw an exception.");

    assertEquals(F1.getMileage(), 0, 0.1, "F1 mileage should be 0");
    assertEquals(F2.getMileage(), 50, 0.1, "F2 mileage should be 50");

    assertEquals(F1.getMPG(), 23.6, 0.1, "F2 MPG should be 23.6");
    assertEquals(F2.getMPG(), 23.6, 0.1, "F2 MPG should be 23.6");

    assertEquals(F1.getFuelLevel(), 20, 0.1, "F1 fuel level should be 20");
    assertEquals(F2.getFuelLevel(), 20, 0.1, "F2 fuel level should be 20");

    assertEquals(F1.getFuelCapacity(), 20, 0.1, "F1 fuel capacity should be 20");
    assertEquals(F2.getFuelCapacity(), 20, 0.1, "F2 fuel capacity should be 20");

    assertEquals(F1.getMake(), "Ford", "F1 make should be Ford");
    assertEquals(F2.getMake(), "Ford", "F2 make should be Ford");

    assertEquals(F1.getModel(), "Frivolous", "F1 model should be Frivolous");
    assertEquals(F2.getModel(), "Frivolous", "F2 model should be Frivolous");

    return getFailedCount();
  }

}
