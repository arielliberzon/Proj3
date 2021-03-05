package sample;

public class Engine {

    private String engineType;
    private double speed;

    public Engine() {
        engineType = "No";
        speed = 0.0;
    }

    public Engine(String engineType) {
        this.engineType = engineType;
        setSpeed(engineType);
    }

    private void setSpeed(String engineType) {
        //FIX
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

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return engineType;
    }
}
