//Nida, Michelle, Alex
package tests;
import vehicle.HondaAccordian;

import java.util.ArrayList;
import java.util.Arrays;

import bcatest.BCATestScenario;

public class Group3_3TestHondaAccordian1 extends BCATestScenario {
    
    @Override
    public int runTest() {

        // Mileage Constructor Tests (sheet 1)
        assertThrows(IllegalArgumentException.class, () -> {new HondaAccordian(-1, 1999);}, "Starting mileage can't be negative, exception should be thrown");
        HondaAccordian a = new HondaAccordian(20, 1999);
        assertFalse(a==null, "Valid mileage and modelYear, object is not properly created.");
        assertEquals(a.getMileage(), 20, 0.1, "Mileage should be 20");
        assertEquals(a.getFuelCapacity(), 14.5, 0.1, "Fuel capacity should be 14.5 in mileage constructor");
        assertEquals(a.getFuelLevel(), 14.5, 0.1, "Fuel level should be 14.5 in mileage constructor");
        assertEquals(a.getMPG(), 33.2, 0.1, "MPG should be 33.2");
        assertEquals(a.toString(), "1999 Honda Accordian (20.0 mi)", "Incorrect toString()  in mileage constructor");

        // GasPoweredCar Methods Tests (sheet 2)
        HondaAccordian b = new HondaAccordian(1999);
        assertFalse(b==null, "Valid modelYear, object is not properly created.");
        assertThrows(IllegalArgumentException.class, () -> {b.drive(-1);}, "Driving a negative distance should have an error");
        b.drive(0.0);
        assertEquals(b.getMileage(), 0, 0.1, "Mileage should stay 0 after driving 0"); 
        b.drive(1.0);
        assertEquals(b.getMileage(), 1, 0.1, "Mileage should be 1 after driving 1");
        b.drive(20.0);
        assertEquals(b.getMileage(), 21, 0.1, "Mileage should be 21 after driving 20");
        assertEquals(b.getRemainingRange(), 460.4, 0.2, "Remaining range should be 460.4 after driving 20");
        b.drive(460.4);
        assertEquals(b.getRemainingRange(), 0, 0.1, "Remaining range should be 0 after driving 460.4");
        assertThrows(IllegalArgumentException.class, () -> {b.drive(1);}, "Driving when remaining range=0 should have an error");
        assertThrows(IllegalArgumentException.class, () -> {b.refillTank(-1);}, "Refilling tank a negative amount should have an error");
        b.refillTank(0.0);
        assertEquals(b.getFuelLevel(), 0, 0.1, "Fuel level should stay zero after filling it with zero gallons");
        b.refillTank(1.0);
        assertEquals(b.getFuelLevel(), 1, 0.1, "Fuel level should be 1 after filling with 1 gallon");
        b.refillTank();
        assertEquals(b.getFuelLevel(), 14.5, 0.1, "Fuel level should be 14.5 after refilling tank fully");
        assertEquals(b.getRemainingRange(), 481.4, 0.2, "Remaining range should be 481.4 after filling tank fully");
        assertThrows(IllegalArgumentException.class, () -> {b.refillTank(1);}, "Should have an error after filling a full tank");
        
        // No Mileage Constructor Tests (sheet 3)
        //assertThrows(IllegalArgumentException.class, () -> {new HondaAccordian(2023);}, "Car model not yet released, exception should be thrown");
        HondaAccordian c = new HondaAccordian(1999);
        assertEquals(c.getMileage(), 0, 0.1, "Mileage should be 0");
        assertEquals(c.getFuelCapacity(), 14.5, 0.1, "Fuel capacity should be 14.5 in no mileage constructor");
        assertEquals(c.getFuelLevel(), 14.5, 0.1, "Fuel level should be 14.5 in no mileage constructor");
        assertEquals(c.getMPG(), 33.2, 0.1, "MPG should be 33.2 in no mileage constructor");
        assertEquals(c.getRemainingRange(), 14.5*33.2, 0.1, "Remaining range should be fuel capacity * mpg in no mileage constructor");
        assertEquals(c.toString(), "1999 Honda Accordian (0.0 mi)", "incorrect toString() in no mileage constructor");

        // RoadTrip Method Tests (sheet 4)
        HondaAccordian d = new HondaAccordian(1999);
        
        ArrayList<Double> d1= new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0)) ;
        ArrayList<Double> d2= new ArrayList<Double>(Arrays.asList(-1.0, -1.0, -1.0, -1.0, -1.0)) ;
        ArrayList<Double> d3= new ArrayList<Double>(Arrays.asList(1.0, -1.0, -1.0, -1.0, -1.0)) ;
        ArrayList<Double> d4= new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0, -1.0)) ;
        ArrayList<Double> d5= new ArrayList<Double>(Arrays.asList(200.0, 100.0, 100.0, 80.0, 5.0)) ;
        ArrayList<Double> d6= new ArrayList<Double>(Arrays.asList(200.0, 100.0, 100.0, 80.0, 1.4)) ;
        ArrayList<Double> d7= new ArrayList<Double>(Arrays.asList(200.0, 100.0, 100.0, 80.0, 1.0)) ;
        ArrayList<Double> d8= new ArrayList<Double>(Arrays.asList(482.0, 1.0, 1.0, 1.0, 1.0)) ;

        assertEquals(d.roadTrip(d1), 5, 0.1, "If you don't drive on a roadtrip you should return length of list");
        assertThrows(IllegalArgumentException.class, () -> {d.roadTrip(d2);}, "Miles in roadTrip can't be negative, exception should be thrown with an all negative list");
        assertThrows(IllegalArgumentException.class, () -> {d.roadTrip(d3);}, "Miles in roadTrip can't be negative, exception should be thrown with list {1.0, -1.0, -1.0, -1.0, -1.0}");
        assertThrows(IllegalArgumentException.class, () -> {d.roadTrip(d4);}, "Miles in roadTrip can't be negative, exception should be thrown with list {1.0, 1.0, 1.0, 1.0, -1.0}");
        d.refillTank();
        assertEquals(d.roadTrip(d5), 4, 0.1, "Roadtrip should run for 4 days before running out of fuel");
        d.refillTank();
        assertEquals(d.roadTrip(d6), 5, 0.1, "Roadtrip should run for 5 days and get through range exactly");
        d.refillTank();
        assertEquals(d.roadTrip(d7), 5, 0.1, "Roadtrip should run for 5 days and with a little fuel to spare");
        d.refillTank();
        assertEquals(d.roadTrip(d8), 0, 0.1, "Roadtrip should not run if the starting day is greater than the car's range");








        /*
        assertThrows(IllegalArgumentException.class, () -> d.roadTrip(d2);, "Miles can't be negative");
        assertThrows(d.getFuelLevel(), 14.5, ""gumentException.class, () -> d.roadTrip({1, -1, -1, -1, -1};), "Miles can't be negative");
        assertThrows(IllegalArgumentException.class, () -> d.roadTrip({1, 1, 1, 1, -1};), "Miles can't be negative");
        d.refillTank();
        assertEquals(5,14.5,  {200, 100, 100, 80, 5}, "Has driven for 4 days");
        assertEquals(d.refillTank(), );
        */




        
        

        return getFailedCount();
    }
}
