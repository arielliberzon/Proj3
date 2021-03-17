package sample;

/**
 * class for the Transmission object that effects the speed of the Car
 */
public class Transmission {


    private String tType;
    private int speed;

    /**
     * default constructor
     */
    public Transmission() {
        tType = "No transmission!";
        speed = 0;
    }

    /**
     * constructor that takes the transmission type as an argument to create a new Transmission object
     * @param tType is the name of the Transmission and will determine the speed factor of the Transmission
     */
    public Transmission(String tType) {
        this.tType = tType;
        setSpeed(tType);
    }

    /**
     * method that sets the speed factor the Transmission based on the Transmission type
     * @param tType will determine the speed factor of the Transmission
     */
    private void setSpeed(String tType) {
        if (tType.equals("Automatic Transmission"))
            speed = 3;
        if (tType.equals("Manual Transmission"))
            speed = 4;
        if (tType.equals("Automated Manual Transmission"))
            speed = 2;
        if (tType.equals("Continuously Variable Transmission"))
            speed = 1;
    }

    /**
     * @return the name of the Transmission
     */
    public String getType() {
        return tType;
    }

    /**
     * method that sets the String/name of the Transmission by taking a String as an parameter
     * @param tType is the name of the specific Transmission
     */
    public void setType(String tType) {
        this.tType = tType;
    }

    /**
     * @return the speed factor of the Transmission
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * creates a String representation of the Transmission
     * @return the String/name of the Transmission
     */
    @Override
    public String toString() {
        return tType;
    }
}
