package tests;

import vehicle.ChevroletBird;
import bcatest.BCATestScenario;

public class TestChevroletBird extends BCATestScenario {
	public int runTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			new ChevroletBird(-100);
		}, "Starting mileage cannot be negative.");

		ChevroletBird c1 = new ChevroletBird(350);
		assertEquals(c1.toString(), "Chevrolet Bird (350.0 mi)", "toString does not match");

		assertEquals(c1.getOdometerMiles(), 350, .1, "Starting mileage should be 350");

		assertEquals(c1.getMaxRange(), 250, .1, "Miles on max charge should be 250");

		assertThrows(IllegalArgumentException.class, () -> {
			c1.drive(-10);
		}, "Driving mileage cannot be negative.");

		c1.drive(200);

		assertEquals(c1.getOdometerMiles(), 550.0, .1, "Mileage should be 550");

		assertEquals(c1.getRemainingRange(), 50.0, .1, "Remaining range should be 50");

		assertFalse(c1.canDrive(55), "Max range is 50, can't drive 55.");
		assertTrue(c1.canDrive(50), "Max range is 50, should be able to drive 50.");

		c1.recharge();
		assertTrue(c1.canDrive(250), "Max range is 250, should be able to drive 250.");
		assertEquals(c1.getRemainingRange(), 250.0, .1, "Remaining range should be 250");
		assertThrows(IllegalArgumentException.class, () -> {
			c1.drive(300);
		}, "should throw IllegalArgumentExcpetion, out of range");

		assertEquals(c1.toString(), "Chevrolet Bird (550.0 mi)", "toString does not match");

		///////////////////////
		// Flying
		//
		ChevroletBird c2 = new ChevroletBird();
		assertThrows(IllegalArgumentException.class, () -> {
			c2.canFly(-5);
		}, "should throw IllegalArgumentException for negative miles.");

		assertFalse(c2.canFly(300), "should return false, out of range");
		assertTrue(c2.canFly(250), "should return true");
		assertThrows(IllegalArgumentException.class, () -> {
			c2.fly(-10);
		}, "should throw IllegalArgumentException for negative miles");
		assertThrows(IllegalArgumentException.class, () -> {
			c2.fly(300);
		}, "should throw IllegalArgumentException, out of range");

		c2.fly(200);
		assertTrue(c2.checkWingsExtended(), "wings should be extended");
		assertEquals(c2.getOdometerMiles(), 0.0, .1, "Flying should not change the odometer.");
		assertEquals(c2.getRemainingRange(), 50, .1, "Range after flying should be 50.");
		assertThrows(IllegalArgumentException.class, () -> {
			c2.fly(60);
		}, "should throw IllegalArgumentException, out of range");

		c2.drive(2);
		assertFalse(c2.checkWingsExtended(), "wings should be closed after driving.");
		assertEquals(c2.getOdometerMiles(), 2.0, .1, "Mileage should be 2");

		return getFailedCount();
	}

}
