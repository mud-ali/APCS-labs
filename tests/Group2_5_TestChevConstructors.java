//by Joelle 

package tests;

import bcatest.BCATestScenario;
import vehicle.ChevroletBird;

public class Group2_5_TestChevConstructors extends BCATestScenario {
  public int runTest() {
    ChevroletBird c1 = new ChevroletBird();

    assertEquals(c1.getMake(), "Chevrolet", "c1 make should be Chevrolet");
    assertEquals(c1.getModel(), "Bird", "c1 model should be Bird.");
    assertFalse(c1.checkWingsExtended(), "Wings extended should be false.");

    assertEquals(c1.getMileage(), 0, .1, "Starting mileage should be correct.");
    assertEquals(c1.toString(), "Chevrolet Bird (0.0 mi)", "toString should work.");
    assertEquals(c1.getMaxRange(), 250, .1, "Max range should be correct.");

    // *****/
    ChevroletBird c2 = new ChevroletBird(20);

    assertEquals(c2.getMake(), "Chevrolet", "c2 make should be Chevrolet.");
    assertEquals(c2.getModel(), "Bird", "c2 model should be Bird.");
    assertFalse(c2.checkWingsExtended(), "Wings extended should be false.");

    assertEquals(c2.getMileage(), 20, .1, "Starting mileage is correct.");
    assertEquals(c2.toString(), "Chevrolet Bird (20.0 mi)", "toString should work.");
    assertEquals(c2.getMaxRange(), 250, .1, "Max range should be correct.");

    // *****/
    assertThrows(IllegalArgumentException.class, () -> {
      ChevroletBird c3 = new ChevroletBird(-50);
    }, "Negative starting mileage should throw an exception.");

    return getFailedCount();
  }
}