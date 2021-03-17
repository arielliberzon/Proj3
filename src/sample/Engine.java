package sample;


/**
 * class for the Engine object that effects the speed of the Car
 */
public class Engine {


    private String engineType;
    private int speed;


    /**
     * default constructor
     */
    public Engine() {
        engineType = "No";
        speed = 0;
    }

    /**
     * constructor that takes the engine type as a parameter to create a new Engine object
     * @param engineType is the name of the Engine and will determine the speed factor of the Engine
     */
    public Engine(String engineType) {
        this.engineType = engineType;
        setSpeed(engineType);
    }

    /**
     * method that sets the speed factor based on the engine type
     * @param engineType will determine the speed factor of the Engine
     */
    private void setSpeed(String engineType) {
        if (engineType.equals("Rotary Engine"))
            speed = 2;
        if (engineType.equals("Diesel Engine"))
            speed = 6;
        if (engineType.equals("Gasoline Engine"))
            speed = 4;
        if (engineType.equals("Radial Engine"))
            speed = 2;
    }

    /**
     * @return the name of the Engine
     */
    public String getEngineType() {
        return engineType;
    }

    /**
     * method that sets the String/name of the Engine
     * @param engineType is the name of the specific Engine
     */
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    /**
     * @return the speed factor of the Engine
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * creates a String representation of the Engine
     * @return the String/name of the Engine
     */
    @Override
    public String toString() {
        return engineType;
    }
}
