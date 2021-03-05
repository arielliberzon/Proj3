package sample;

public class Simulator {

    private Car[] cars;
    private Track track;
    private int numCars;
    private double time;

    public Simulator() {
    }

    public int getNumCars() {
        return numCars;
    }

    public void setNumCars(int numCars) {
        this.numCars = numCars;
        cars = new Car[numCars];
    }

    public Car getCar(int i) {
        return cars[i];
    }

    public void addCar(int i, Car car) {
        //
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void startRace() {

    }

    public int findWinner() {
        return  -1;
    }



}
