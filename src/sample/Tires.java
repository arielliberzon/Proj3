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
        if (tireType.equals("All-Season"))
            setSpeed(1);
        if (tireType.equals("Touring"))
            setSpeed(2);
        if (tireType.equals("Performance"))
            setSpeed(3);
        if (tireType.equals("Track & Competition"))
            setSpeed(4);
    }

    public String getTireType() {
        return tireType;
    }

    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    public int getSpeed() {
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
