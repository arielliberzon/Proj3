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
        if (tType.equals("Transmission 1"))
            setSpeed(1);
        if (tType.equals("Transmission 2"))
            setSpeed(2);
        if (tType.equals("Transmission 3"))
            setSpeed(3);
        if (tType.equals("Transmission 4"))
            setSpeed(4);
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
