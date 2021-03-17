package sample;

public class Transmission {
    // DELETE THIS LINE

    private String tType;
    private int speed;

    public Transmission() {
        tType = "No transmission!";
        speed = 0;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return tType;
    }
}
