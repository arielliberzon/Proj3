package sample;

public class Engine {

    // DELETE THIS LINE
    private String engineType;
    private int speed;

    public Engine() {
        engineType = "No";
        speed = 0;
    }

    public Engine(String engineType) {
        this.engineType = engineType;
        setSpeed(engineType);
    }

    private void setSpeed(String engineType) {
        if (engineType.equals("Rotary Engine"))
            speed = 3;
        if (engineType.equals("Diesel Engine"))
            speed = 6;
        if (engineType.equals("Gasoline Engine"))
            speed = 4;
        if (engineType.equals("Radial Engine"))
            speed = 2;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return engineType;
    }
}
