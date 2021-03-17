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
        if (tType.equals("Automatic Transmission"))
            setSpeed(3);
        if (tType.equals("Manual Transmission"))
            setSpeed(4);
        if (tType.equals("Automated Manual Transmission"))
            setSpeed(2);
        if (tType.equals("Continuously Variable Transmission"))
            setSpeed(1);
    }

    public String getType() {
        return tType;
    }

    public void setType(String tType) {
        this.tType = tType;
    }

    public int getSpeed() {
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
