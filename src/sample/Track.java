package sample;

public class Track {

    private Car[] cars;
    //private double[] time;
    private final char[] checkpoint = {'A', 'B', 'C', 'D'};

    public Track(Car[] cars) {
        this.cars = cars;
    }

    public Car getCars(int i) {
        return cars[i];
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    public void setNextCheckPoint(int i) {
        //DO SOMETHING
    }

    private void turnCar(int i) {
        //
    }

    public void markCompleted(int i) {
        //FIX
    }


    @Override
    public String toString() {
        return "FIX!";
    }
}
