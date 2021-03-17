package sample;

public class Tires {
    // DELETE THIS LINE

    private String tireType;
    private int speed;

    public Tires() {
        tireType = "No tires!";
        speed = 0;
    }

    public Tires(String tireType) {
        this.tireType = tireType;
        setSpeed(tireType);
    }

    private void setSpeed(String tireType) {
        if (tireType.equals("Tires 1"))
            setSpeed(1);
        if (tireType.equals("Tires 2"))
            setSpeed(1);
        if (tireType.equals("Tires 3"))
            setSpeed(1);
        if (tireType.equals("Tires 4"))
            setSpeed(1);
    }

    public String getTireType() {
        return tireType;
    }

    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return tireType;
    }
}
