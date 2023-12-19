//Nida, Michelle, Alex
package tests;
import vehicle.FordFrivolous;

import java.util.ArrayList;
import java.util.Arrays;

import bcatest.BCATestScenario;

public class Group3_3TestFordFrivolous extends BCATestScenario {
    @Override
    public int runTest() {
        
        // Mileage Constructor Tests (sheet 1)
        assertThrows(IllegalArgumentException.class, () -> {new FordFrivolous(-1.0);}, "Starting mileage can't be negative");
        FordFrivolous testing= new FordFrivolous(0);
        assertFalse(testing==null, "Valid mileage, object is not properly created");
        FordFrivolous a= new FordFrivolous(15);
        assertEquals(a.getMileage(), 15, 0.1, "Mileage should equal 15 when parameter is 15");
        assertEquals(a.getFuelCapacity(), 20, 0.1, "Fuel capacity should be 20 in mileage constructor");
        assertEquals(a.getFuelLevel(), 20, 0.1, "Fuel level should be 20 in mileage constructor");
        assertEquals(a.getMPG(), 23.6, 0.1, "MPG should equal 23.6 after instantiated in mileage constructor");
        assertEquals(a.toString(), "Ford Frivolous (15.0 mi)", "Incorrect toString() in mileage constructor");


        
        // GasPoweredCar Methods Tests (sheet 2)
        FordFrivolous b = new FordFrivolous();
        assertThrows(IllegalArgumentException.class, () -> {b.drive(-1);}, "Driving a negative distance should have an error");
        b.drive(0.0);
        assertEquals(b.getMileage(), 0, 0.1, "Mileage should stay 0 after driving 0"); 
        b.drive(1.0);
        assertEquals(b.getMileage(), 1, 0.1, "Mileage should be 1 after driving 1");
        b.drive(20.0);
        assertEquals(b.getMileage(), 21, 0.1, "Mileage should be 21 after driving 20");
        assertEquals(b.getRemainingRange(), 451, 0.2, "Remaining range should be 451 after driving 20");
        b.drive(451);
        assertEquals(b.getRemainingRange(), 0, 0.1, "Remaining range should be 0 after driving 451");
        assertThrows(IllegalArgumentException.class, () -> {b.drive(1);}, "Driving when remaining range=0 should have an error");
        assertThrows(IllegalArgumentException.class, () -> {b.refillTank(-1);}, "Refilling tank a negative amount should have an error");
        b.refillTank(0.0);
        assertEquals(b.getFuelLevel(), 0, 0.1, "Fuel level should stay zero after filling it with zero gallons");
        b.refillTank(1.0);
        assertEquals(b.getFuelLevel(), 1, 0.1, "Fuel level should be 1 after filling with 1 gallon");
        b.refillTank();
        assertEquals(b.getFuelLevel(), 20, 0.1, "Fuel level should be 20 after refilling tank fully");
        assertEquals(b.getRemainingRange(), 472, 0.2, "Remaining range should be 472 after filling tank fully");
        assertThrows(IllegalArgumentException.class, () -> {b.refillTank(1);}, "Should have an error after filling a full tank");
        
        // Empty Constructor Tests (sheet 3)
        FordFrivolous c = new FordFrivolous();
        assertEquals(c.getMileage(), 0, 0.1, "Mileage should be 0");
        assertEquals(c.getFuelCapacity(), 20, 0.1, "Fuel capacity should be 20 in empty constructor");
        assertEquals(c.getFuelLevel(), 20, 0.1, "Fuel level should be 20 in empty constructor");
        assertEquals(c.getMPG(), 23.6, 0.1, "MPG should be 23.6 in empty constructor");
        assertEquals(c.getRemainingRange(), 20*23.6, 0.1, "Remaining range should be fuel capacity * mpg in empty constructor");
        assertEquals(c.toString(), "Ford Frivolous (0.0 mi)", "incorrect toString() in empty constructor");


        // RoadTrip Method Tests (sheet 4)
        FordFrivolous d = new FordFrivolous();
        
        ArrayList<Double> d1= new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0)) ;
        ArrayList<Double> d2= new ArrayList<Double>(Arrays.asList(-1.0, -1.0, -1.0, -1.0, -1.0)) ;
        ArrayList<Double> d3= new ArrayList<Double>(Arrays.asList(1.0, -1.0, -1.0, -1.0, -1.0)) ;
        ArrayList<Double> d4= new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0, -1.0)) ;
        ArrayList<Double> d5= new ArrayList<Double>(Arrays.asList(200.0, 100.0, 100.0, 70.0, 5.0)) ;
        ArrayList<Double> d6= new ArrayList<Double>(Arrays.asList(200.0, 100.0, 100.0, 70.0, 2.0)) ;
        ArrayList<Double> d7= new ArrayList<Double>(Arrays.asList(200.0, 100.0, 100.0, 70.0, 1.0)) ;
        ArrayList<Double> d8= new ArrayList<Double>(Arrays.asList(473.0, 1.0, 1.0, 1.0, 1.0)) ;

        assertEquals(d.roadTrip(d1), 5, 0.1, "If you don't drive on a roadtrip you should return length of list");
        assertThrows(IllegalArgumentException.class, () -> {d.roadTrip(d2);}, "Miles in roadTrip can't be negative, exception should be thrown with an all negative list");
        assertThrows(IllegalArgumentException.class, () -> {d.roadTrip(d3);}, "Miles in roadTrip can't be negative, exception should be thrown with list {1.0, -1.0, -1.0, -1.0, -1.0}");
        assertThrows(IllegalArgumentException.class, () -> {d.roadTrip(d4);}, "Miles in roadTrip can't be negative, exception should be thrown with list {1.0, 1.0, 1.0, 1.0, -1.0}");
        assertEquals(d.roadTrip(d5), 4, 0.1, "Roadtrip should run for 4 days before running out of fuel");
        d.refillTank();
        assertEquals(d.roadTrip(d6), 5, 0.1, "Roadtrip should run for 5 days and get through range exactly");
        d.refillTank();
        assertEquals(d.roadTrip(d7), 5, 0.1, "Roadtrip should run for 5 days and with a little fuel to spare");
        d.refillTank();
        assertEquals(d.roadTrip(d8), 0, 0.1, "Roadtrip should not run if the starting day is greater than the car's range");
        

        return getFailedCount();
    }
}
