package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Car extends Rectangle implements Comparable<Car> {
//test
    private int place;

    private int carNum;

    private Color color;
    //Attributes not used yet
    private Transmission transmission;
    private Engine engineHP;
    //private int torque;
    private Tires tireRating;
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

    //Constructor
    public Car(int orientation, double x, double y, int speed, Color color) {
        this.orientation = orientation;
        setX(x);
        setY(y);
        setFill(Color.LIGHTBLUE);
        this.speed = speed;
        setFill(color);
        setSizes();
    }

    public Car(int carNum, String name, Engine engineHP, Tires tireRating,
               Transmission transmission, Color color) {
        this.carNum = carNum;
        this.name = name;
        this.engineHP = engineHP;
        this.tireRating = tireRating;
        this.transmission = transmission;
        this.color = color;
        speed = 2;
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

    public void setCarStats(String stats){

        carStats = stats;
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

    public Engine getEngineHP() {
        return engineHP;
    }

    public void setEngineHP(Engine engineHP) {
        this.engineHP = engineHP;
    }

    /*public int getTorque() {
        return torque;
    }
    public void setTorque(int torque) {
        this.torque = torque;
    }*/

    public Tires getTireRating() {
        return tireRating;
    }

    public void setTireRating(Tires tireRating) {
        this.tireRating = tireRating;
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

    public void setSpeed(int speed) {
        this.speed = speed;
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