package sample;

public class Tires {

    private String tireType;
    private double speed;

    public Tires() {
        tireType = "No tires!";
        speed = 0.0;
    }

    public Tires(String tireType) {
        this.tireType = tireType;
        setSpeed(tireType);
    }

    private void setSpeed(String tireType) {
        //FIX
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

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return tireType;
    }
}
