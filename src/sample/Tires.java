package sample;

/**
 * class for the Tire object that affects the speed of the Car
 */
public class Tires {


    private String tireType;
    private int speed;

    /**
     * default constructor
     */
    public Tires() {
        tireType = "No tires!";
        speed = 0;
    }

    /**
     * constructor that takes the tireType as an argument to create a new Tires object
     * @param tireType is the name of the Tires and will determine the speed factor of the Tires
     */
    public Tires(String tireType) {
        this.tireType = tireType;
        setSpeed(tireType);
    }

    /**
     * method that sets the speed factor based on the tire type
     * @param tireType will determine the speed factor of the Transmission
     */
    private void setSpeed(String tireType) {
        if (tireType.equals("All-Season"))
            speed = 1;
        if (tireType.equals("Touring"))
            speed = 2;
        if (tireType.equals("Performance"))
            speed = 3;
        if (tireType.equals("Track & Competition"))
            speed = 4;
    }

    /**
     * @return the name of the Tires
     */
    public String getTireType() {
        return tireType;
    }

    /**
     * method that sets the String/name of the Tires by taking a String as a parameter
     * @param tireType is the name of the specific Tires
     */
    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    /**
     * @return the speed factor of the Tires
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * creates a String representation of the Tires
     * @return the String/name of the Tires
     */
    @Override
    public String toString() {
        return tireType;
    }
}
