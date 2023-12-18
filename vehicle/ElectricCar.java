package vehicle;

public abstract class ElectricCar extends Car {
    
    private double milesOnMaxCharge;

    /**
     * Note: Car begins with a full charge (and thus range).
     * @throws IllegalArgumentException if milesOnMaxCharge is nonpositive
     */
    public ElectricCar(String make, String model, double startingOdometerValue, double milesOnMaxCharge) {
        super(make, model, startingOdometerValue);
        this.milesOnMaxCharge = milesOnMaxCharge;
    }

    /**
     * Defaults startingOdometerValue to 0.
     * @throws IllegalArgumentException if milesOnMaxCharge is nonpositive
     */
    public ElectricCar (String make, String model, double milesOnMaxCharge) {
        this(make, model, 0, milesOnMaxCharge);
    }

    /**
     * Drives the full given number of miles.
     * @throws IllegalArgumentException if miles is negative.
     * @throws IllegalArgumentException if miles is too high given the current charge.
     */
    public void drive(double miles) {
        if (miles < 0 || getRemainingRange() < miles)
        if (canDrive(miles)) {
            addMiles(miles);
            decreaseCharge(miles);   
        }
        else throw new IllegalArgumentException("cannot drive "+miles+" miles");
    }

    /**
     * Returns how many more miles the car can currently go without
     * recharging.
     */
    public double getRemainingRange() {
        
    }

    /**
     * Returns how many miles the car could go on a full charge.
     */
    public double getMaxRange() {}
    
    /** Recharges the car to max range capability. */
    public void recharge() {}
    /** Decreases the amount of energy in the battery based by the number
    of miles passed as an argument. */
    protected void decreaseCharge(double miles) {}

}
