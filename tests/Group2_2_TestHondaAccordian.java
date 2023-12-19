// JP

package tests;

import bcatest.BCATestScenario;
import vehicle.HondaAccordian;
import java.util.*;

public class Group2_2_TestHondaAccordian extends BCATestScenario {

	public int runTest() {

		assertThrows(
				IllegalArgumentException.class,
				() -> {
					HondaAccordian c = new HondaAccordian(-1, 2022);
				},
				"Illegal Argument Exception not thrown");

		// Split tests with particular methods that are being called

		// With 0 as starting mileage;
		runConstructorTest(0, 2022);

		// With no starting mileage;
		runConstructorTest(2018);

		// With a nonzero value as the starting mileage;
		runConstructorTest(15, 2022);

		// Methods involved are drive with different parameters
		runDriveTest();

		// Calls all methods correctly involving to get/return value
		runGetterTest();

		// Calls all methods that involve using and refilling gas
		runFuelTest();

		// Calls all methods from Car, to confirm they work
		runCarTest();

		return getFailedCount();
	}

	private void runConstructorTest(double mileage, int year, boolean twoArg) {
		HondaAccordian c = new HondaAccordian(year);
		if (twoArg) {
			c = new HondaAccordian(mileage, year);
		}
		assertEquals(c.getRemainingRange(), mileage, 0, "Mileage is not set to " + mileage);
		assertEquals(c.getMake(), "Honda", "Make is not Honda");
		assertEquals(c.getModel(), "Accordian", "Model is not Accordian");
		assertEquals(c.toString(), year + " Honda Accordian (" + mileage + " mi)", "toString() is incorrect");
		assertEquals(c.getMPG(), 33.2, 0.01, "MPG is not 33.2");
		assertEquals(c.getFuelCapacity(), 14.5, 0.01, "Fuel capicity is not 14.5");
		assertEquals(c.getFuelLevel(), 14.5, 0.01, "Fuel level is not 14.5");
		assertEquals(c.getFuelLevel(), c.getFuelCapacity(), 0.01, "Car does not start with a full tank.");
	}

	private void runConstructorTest(int year) {
		runConstructorTest(0, year, false);
	}

	private void runConstructorTest(double mileage, int year) {
		runConstructorTest(mileage, year, true);
	}

	private void runDriveTest() {
		HondaAccordian c = new HondaAccordian(2022);
		c.drive(10);
		assertEquals(c.getRemainingRange(), 10, 0.01, "Mileage is not set to 10");
		c.drive(50);
		assertEquals(c.getRemainingRange(), 60, 0.01, "Mileage is not set to 60");
		c.drive(0);
		assertEquals(c.getRemainingRange(), 60, 0.01, "Mileage is not set to 60");
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					c.drive(-1);
				},
				"Illegal Argument Exception not thrown");
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					c.drive(c.getMPG() * c.getFuelLevel() + 1);
				},
				"Illegal Argument Exception not thrown");
		c.drive(c.getMPG() * c.getFuelLevel());
		assertEquals(c.getRemainingRange(), 481.4, .01, "Mileage not set to 481.4");
		assertEquals(c.getRemainingRange(), c.getFuelCapacity() * c.getMPG(), .01,
				"Mileage does not match max milage per tank");
		assertEquals(c.getFuelLevel(), 0, .01, "Fuel level is not 0");
	}

	private void runGetterTest() {
		HondaAccordian c = new HondaAccordian(2022);
		c.drive(50);
		assertEquals(c.getRemainingRange(), 50, 0, "Mileage is not set to 50");
		// assertTrue(c.getRemainingRange() == c.getMPG() * c.getFuelCapacity() - 50,
		// "Remaining miles do not match driven miles");
		assertTrue(c.getFuelLevel() == c.getFuelCapacity() - (50 / c.getMPG()),
				"Fuel level does not match driven miles");
	}

	private void runFuelTest() {
		HondaAccordian c = new HondaAccordian(2022);
		c.drive(c.getMPG());
		assertEquals(c.getFuelLevel(), 13.5, .01, "Fuel level is not 13.5");
		c.refillTank(1);
		assertEquals(c.getFuelLevel(), 14.5, .01, "Fuel level is not 14.5");
		c.drive(2 * c.getMPG());
		assertEquals(c.getFuelLevel(), 12.5, .01, "Fuel level is not 12.5");
		c.drive(0);
		assertEquals(c.getFuelLevel(), 12.5, .01, "Fuel level is not 12.5");
		c.refillTank(1);
		assertEquals(c.getFuelLevel(), 13.5, .01, "Fuel level is not 13.5");
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					c.refillTank(2);
				},
				"Illegal Argument Exception not thrown");
		assertThrows(
				IllegalArgumentException.class,
				() -> {
					c.refillTank(-1);
				},
				"Illegal Argument Exception not thrown");
	}

	private void runCarTest() {

		HondaAccordian c = new HondaAccordian(15, 2010);

		assertThrows(IllegalArgumentException.class, () -> {
			c.canDrive(-5);
		}, "Cannot drive negative miles.");

		assertTrue(c.canDrive(c.getMPG() * c.getFuelLevel() + 1) == false, "Can't drive over the remaining range.");
		assertTrue(c.canDrive(8) == true, "Can drive under the remaining range.");

		assertTrue(c.getRemainingRange() == 15, "Mileage should be 0 miles.");
		assertTrue(c.getMake().equals("Honda"), "Make is Honda.");

		assertTrue(c.toString().equals("2010 Honda Accordian (15.0 mi)"),
				"Should return 2010 Honda Accordian (15.0 mi)");

		List<Double> l1 = null;
		List<Double> l2 = new ArrayList<Double>();
		l2.add(-1.0);
		List<Double> l3 = new ArrayList<Double>();
		l3.add(43.0);
		l3.add(52.0);
		l3.add(39.0);

		assertThrows(NullPointerException.class, () -> {
			c.roadTrip(l1);
		}, "List cannot reference to null");

		assertThrows(IllegalArgumentException.class, () -> {
			c.roadTrip(l2);
		}, "Value cannot be negative for miles.");

		assertTrue(c.roadTrip(l3) == 3, "They could drive for 3 days.");
	}
}