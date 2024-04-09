package vehicle;

public class ChevroletBird extends ElectricCar implements Flying {

    private boolean wingsOpen;

    /**
     * Chevrolet Birds have a 250-mile range on a full charge. They
     * start with their wings retracted.
     */
    public ChevroletBird(double startingOdometerValue) {
        super("Chevrolet", "Bird", startingOdometerValue, 250.0);
        wingsOpen = false;
    }

    /**
     * Defaults startingOdometerValue to 0.
     */
    public ChevroletBird() {
        this(0.0);
    }

    /** Returns whether the wings are currently extended. */
    public boolean checkWingsExtended() {
        return wingsOpen;
    }

    /**
     * Drives just like all other Electric Cars, except make sure that
     * you retract your wings first!
     */
    public void drive(double miles) {
        if (checkWingsExtended())
            wingsOpen = false;
        super.drive(miles);
    }

    /**
     * Flying equivalent of canDrive.
     * 
     * @throws IllegalArgumentException if miles is negative.
     */
    public boolean canFly(double miles) {
        if (miles < 0) {
            throw new IllegalArgumentException();
        }
        return canDrive(miles);
    }

    /**
     * Flying extends the wings! It’s basically the same as driving, and
     * uses the same amount of gas as driving per mile – but since wheels
     * aren’t involved, it doesn’t alter the odometer. Note that fly()
     * doesn’t close the wings when it lands -- what if it needs to take-off
     * again in a minute? Too much opening and closing! Instead, drive()
     * is responsible for closing the wings if they are extended. (Extended
     * wings are dangerous on the highway!)
     * 
     * @throws IllegalArgumentException if miles is negative.
     * @throws IllegalArgumentException if miles exceeds the remaining range
     *                                  of the car.
     */
    public void fly(double miles) {
        if (!canFly(miles)) {
            throw new IllegalArgumentException("Cannot fly the given distance");
        }
        wingsOpen = true;
        decreaseCharge(miles);
    }
}
