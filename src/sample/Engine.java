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
        if (engineType.equals("Engine 1"))
            speed = 1;
        if (engineType.equals("Engine 2"))
            speed = 1;
        if (engineType.equals("Engine 3"))
            speed = 1;
        if (engineType.equals("Engine 4"))
            speed = 1;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public double getSpeed() {
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
