package vehicle;

public class HondaAccordian extends GasPoweredCar {

    private int year;

    /**
     * modelYear specifies the year this car was made. Honda cares about
     * that stuff. All Honda Accordian models have 14.5 gallon tanks and
     * 33.2 MPG. */
    public HondaAccordian(double startingOdometerValue, int modelYear) {
        super("Honda", "Accordion", startingOdometerValue, 33.2, 14.5);
        this.year = modelYear;
    }

    /** Defaults startingOdometerValue to 0. */
    public HondaAccordian(int year) {
        this(0, year);
    }
    
    /** Prints out the model year, make and model, and odometer miles.
    Ex: "2019 Honda Accordian (<miles> mi)"
    Coding tip: Write this method to re-use the behavior of the
    superclass toString. Don’t copy-and-paste the same code here. */
    public String toString() {
        return year+" "+super.toString();
    }
}
