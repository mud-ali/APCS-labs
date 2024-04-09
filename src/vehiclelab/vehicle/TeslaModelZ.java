package vehicle;

public class TeslaModelZ extends ElectricCar implements SelfDriving {

    private int modelNum;

    /**
     * modelNum specifies the model number. Tesla cares about that
     * stuff. Tesla Model Z’s have a 340 mile range on a full charge.
     * For a Tesla, the make is Tesla. The model is Z. The model number is
     * an additional value.
     **/
    public TeslaModelZ (double startingOdometerValue, int modelNum) {
        super("Tesla","Z",startingOdometerValue, 340);
        this.modelNum = modelNum;
    }

    /** Defaults startingOdometerValue to 0. */
    public TeslaModelZ (int modelNum) {
        this(0.0, modelNum);
    }

    /** Returns the model number.*/
    public int getModelNum() {
        return modelNum;
    }

    /**
     * Returns the model and model number concatenated together. For
     * example, returns "Z70" for modelNum 70.
     */
    @Override
    public String getModel() {
        return "Z"+modelNum;
    }

    /**
     * Driving autonomously works the same as regular driving does.
     * Very convenient! Except it doesn’t deal with fueling at all – if you
     * can’t make it all the way, it drives as far as it can before running
     * out of fuel.
     * @throws IllegalArgumentException if miles is negative.
     */
    public void driveAutonomously(double miles) {
        if (miles < 0) throw new IllegalArgumentException("miles must be positive");
        drive(Math.min(miles, getRemainingRange()));
    }
}
