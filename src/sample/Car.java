package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

public class Car extends Circle {

    private int place;

    private int carNum;

    private Color color;
    //Attributes not used yet
    private Transmission transmission;
    private Engine engineHP;
    //private int torque;
    private Tires tireRating;
    private int weight;
    private int handlingRating;
    private String model;
    private String year;
    private String brand;

    //Holds initial time
    private Instant start;

    //Holds final time
    private Instant finish;

    //Hold race time
    private int time;

    //Depending on number keep track of orientation (change to string maybe)
    private int orientation;

    //Route is planned when car is placed in track
    private LinkedList<CheckPoint> route = new LinkedList<CheckPoint>();

    //Will be later calculated depending on attributes
    private int speed;

    //Keeps track which checkpoint in route car car most recently passed (0 to 3)
    private int currentCP = 0;

    //Whether or not car is still racing
    private boolean active = true;

    //Keeps track of length traveled
    private int odometer;

    //Constructor
    public Car(int orientation, double x, double y, int speed, Color color, String brand) {
        this.orientation = orientation;
        setCenterX(x);
        setCenterY(y);
        setRadius(5);
        setFill(Color.LIGHTBLUE);
        this.speed = speed;
        setFill(color);
        this.brand = brand;
    }

    public Car(int carNum, String model, Engine engineHP, Tires tireRating,
               Transmission transmission, Color color) {
        this.carNum = carNum;
        this.model = model;
        this.engineHP = engineHP;
        this.tireRating = tireRating;
        this.transmission = transmission;
        this.color = color;
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
    public int endTime(){
        finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        time = (int) (timeElapsed / 1000);
        return time;
    }

    //toString to debug


    @Override
    public String toString() {
        return "Car{" +
                "name=" + model +
                "color=" + color +
                ", transmission=" + transmission +
                ", engineHP=" + engineHP +
                ", tireRating=" + tireRating +
                '}';
    }

    //Used so far getters and setters
    public int getCurrentCP() {
        return currentCP;
    }

    public void setRoute(LinkedList<CheckPoint> route) {
        this.route = route;
    }

    public LinkedList<CheckPoint> getRoute() {
        return route;
    }

    public int getTime() {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHandlingRating() {
        return handlingRating;
    }

    public void setHandlingRating(int handlingRating) {
        this.handlingRating = handlingRating;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
