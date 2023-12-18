package vehicle;

import java.util.List;

public abstract class Car {
    private String make;
    private String model;
    private double miles;

    /**
     * Creates a car with starting total miles on the odometer.
     * @throws IllegalArgumentException if startingOdometerValue is negative
     */
    public Car(String make, String model, double startingOdometerValue) {
        this.make = make;
        this.model = model;
        if (startingOdometerValue < 0)
            throw new IllegalArgumentException("Odometer value must not be negative");
        this.miles = startingOdometerValue;
    }
    
    /** Defaults startingOdometerValue to 0. */
    public Car(String make, String model) {
        this(make, model, 0.0);
    }

    /**
     * If able to drive the full given number of miles, returns true. If
     * not, returns false. (Determination is based upon how far the car can
     * drive given the remaining fuel/energy reserves.)
     * @throws IllegalArgumentException if miles is negative.
     */
    public boolean canDrive(double miles) {
        if (miles < 0)
            throw new IllegalArgumentException("Miles cannot be a negative value");
        return miles <= getRemainingRange();
    }

    /**
     * Drives the full given number of miles.
     * @throws IllegalArgumentException if miles is negative or if miles is
     * too high given the current fuel.
     */
    public abstract void drive(double miles);

    /**
     * Gives String representation of Car as
     * "<make and model> (<miles> mi)"
     * Odometer miles should be rounded to 1 decimal place. If miles is a
     * whole number, ".0" should still display.
     */
    public String toString() {
        return String.format("%s %s (%0.2f mi)", make, model, getOdometerMiles());
    }

    /** Returns how many miles have been driven so far (odometer). */
    public double getOdometerMiles() {
        return this.miles;
    }

    /** Returns the make of the car. */
    public String getMake() {
        return make;
    }

    /** Returns the model of the car. */
    public String getModel() {
        return this.model;
    }

    /**
     * Returns how many more miles the car can currently go given the
     * remaining fuel/energy reserves.
     */
    public abstract double getRemainingRange();

    /**
     * Adds miles to the odometer.
     * @throws IllegalArgumentException if miles is negative.
     */
    protected void addMiles(double miles) {
        this.miles += miles;
    }

    /**
     * The car attempts to drive, in order, each of the daily number of
     * miles in the list milesEachDay. Once the car cannot drive one of the
     * day’s distance, no more days are attempted. Returns the number of
     * days successfully driven.
     * @throws IllegalArgumentException if miles is negative for any of the
     * attempted days. The exception check should occur prior to any driving
     * is attempted.
     */
    public int roadTrip(List<Double> milesEachDay) {
        for (int i=0;i<milesEachDay.size();i++) {
            if (milesEachDay.get(i) < 0)
                throw new IllegalArgumentException("Attempted to drive a negative number of miles");
        }
        int daysDriven = 0;
        for (int i=0;i<milesEachDay.size();i++) {
            try {
                drive(milesEachDay.get(i));
            } catch (IllegalArgumentException ex) {
                daysDriven = i;
                break;
            }
        }
        return daysDriven;
    }
}