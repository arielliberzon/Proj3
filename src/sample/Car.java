package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Car extends Rectangle implements Comparable<Car> {
    // DELETE THIS LINE
    private int place;

    private int carNum;

    private Color color;
    //Attributes not used yet
    private Transmission transmission;
    private Engine engine;
    //private int torque;
    private Tires tires;
    private String name;

    //Holds initial time
    private Instant start;

    //Holds final time
    private Instant finish;

    //Hold race time
    private double time;

    //Depending on number keep track of orientation (change to string maybe)
    private int orientation;

    //Route is planned when car is placed in track
    private ArrayList<CheckPoint> route = new ArrayList<CheckPoint>();

    //Will be later calculated depending on attributes
    private int speed;

    //Keeps track which checkpoint in route car car most recently passed (0 to 3)
    private int currentCP = 0;

    //Whether or not car is still racing
    private boolean active = true;

    //Keeps track of length traveled
    private int odometer;

    private String carStats;

    public Car() {
        carNum = 0;
        name = "No Name";
        engine = new Engine();
        tires = new Tires();
        transmission = new Transmission();
        color = Color.BLACK;
        speed = 1;
        time = 0.0;
        odometer = 1200;
    }

    public Car(int carNum, String name, Engine engine, Tires tires,
               Transmission transmission, Color color) {
        this.carNum = carNum;
        this.name = name;
        this.engine = engine;
        this.tires = tires;
        this.transmission = transmission;
        this.color = color;
        setFill(color);
        setSpeed();
    }

    public void setSizes(){
        if(orientation == 1 || orientation == 3){
            setWidth(20);
            setHeight(10);
        }
        else {
            setWidth(10);
            setHeight(20);
        }
    }

    public void rotate(){
        double saved = getWidth();
        setWidth(getHeight());
        setHeight(saved);
    }

    //Update last checkpoint passed
    public void incrementCP() {
        currentCP++;
    }

    //Will calculate speed
    public void calculateSpeed(){
        speed = 1;
    }

    //Calculates time taken
    public double endTime(){
        finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        time = (double) (timeElapsed / 1000);
        return time;
    }

    @Override
    public int compareTo(Car other){

        return (int) (other.getTime() * 1000 - this.getTime() * 1000);
    }

    //toString to debug


    @Override
    public String toString() {
        return name;
    }

    //Used so far getters and setters
    public int getCurrentCP() {
        return currentCP;
    }

    public void setRoute(ArrayList<CheckPoint> route) {
        this.route = route;
    }

    public ArrayList<CheckPoint> getRoute() {
        return route;
    }

    public double getTime() {
        return time;
    }

    public int getSpeed() {
        return speed;
    }

    public void startTime(){
        start = Instant.now();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public void setCarStats(int place){
        if (place == 1)
            carStats = name + " finished 1st with a time of " + time + ".";
        if (place == 2)
            carStats = name + " finished 2nd with a time of " + time + ".";
        if (place == 3)
            carStats = name + " finished 3rd with a time of " + time + ".";
        if (place == 4)
            carStats = name + " finished 4th with a time of " + time + ".";
        if (place > 4)
            carStats = "place # is: " + place;
    }

    public String getCarStats(){
        return carStats;
    }


    //Getters and setters not used

    public void setTime(int time) {
        this.time = time;
    }

    public String returnRoute(){
        return route.toString();
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Engine getengine() {
        return engine;
    }

    public void setengine(Engine engine) {
        this.engine = engine;
    }

    public Tires gettires() {
        return tires;
    }

    public void settires(Tires tires) {
        this.tires = tires;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getFinish() {
        return finish;
    }

    public void setFinish(Instant finish) {
        this.finish = finish;
    }

    public void setSpeed() {
        speed = engine.getSpeed() + tires.getSpeed() + transmission.getSpeed();
    }

    public void setCurrentCP(int currentCP) {
        this.currentCP = currentCP;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}