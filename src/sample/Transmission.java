package sample;

public class Transmission {

    private String tType;
    private double speed;

    public Transmission() {
        //FIX
    }

    public Transmission(String tType) {
        this.tType = tType;
        setSpeed(tType);
    }

    private void setSpeed(String tType) {
        speed = 0.0;
    }

    public String getType() {
        return tType;
    }

    public void setType(String tType) {
        this.tType = tType;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return tType;
    }
}
