/**
 * This Car class reflects the players choices when they start the game. Every car has an
 * engine, tires, transmission, and color that the user can chose (up to 4 options for
 * each). Car is based on a rectangle and its orientation reflects its appearance on the
 * track. Every car also has a set of X and Y coordinates that determine its place on the
 * track along with references to its starting checkpoint and route.
 */
package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;

public class Car extends Rectangle {

    private int carNum;
    private Color color;
    private Transmission transmission;
    private Engine engine;
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

    private int place;

    /**
     * Default Constructor
     * used in race track
     */
    public Car() {
        carNum = 0;
        name = "No Name";
        engine = new Engine();
        tires = new Tires();
        transmission = new Transmission();
        color = Color.BLACK;
        setFill(color);
        speed = 1;
        time = 0.0;
    }

    /**
     * Constructor used to create cars in the StartMenu class
     * @param carNum Player number (1-4)
     * @param name StartMenu sets this as "Car #(carNum)"
     * @param engine
     * @param tires
     * @param transmission
     * @param color
     */
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

    /**
     * Set the size of the car based on the orientation
     */
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

    //Calculates time taken
    public double endTime(){
        finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        time = (timeElapsed / 1000.0);
        return time;
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

    public void updateOdometer(int extraMiles) {
        odometer += extraMiles;
    }

    /**
     * Sets the carStats String based on the car's placement in the race
     * @param place value passed by the RaceTrack class
     * M. Skuncik
     */
    public void setCarStats(int place){
        if (place == 1)
            carStats = name + " finished 1st with a time of " + time + " seconds.";
        if (place == 2)
            carStats = name + " finished 2nd with a time of " + time + " seconds.";
        if (place == 3)
            carStats = name + " finished 3rd with a time of " + time + " seconds.";
        if (place == 4)
            carStats = name + " finished 4th with a time of " + time + " seconds.";
        if (place > 4)
            carStats = "place # is: " + place;
    }

    /**
     *
     * @return
     */
    public String getCarStats(){
        return carStats;
    }

    /**
     * Adds up the speed value for all of the car's relevant components
     * sets that value as the car's speed
     * M. Skuncik
     */
    private void setSpeed() {
        speed = engine.getSpeed() + tires.getSpeed() + transmission.getSpeed();
    }

    /**
     * Overridden toString method
     * @return Car name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Overridden equals method
     * @param o Object
     * @return true if they're the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car that = (Car) o;
        return carNum == that.carNum &&
                Objects.equals(name, that.name) &&
                Objects.equals(engine, that.engine) &&
                Objects.equals(tires, that.tires) &&
                Objects.equals(transmission, that.transmission) &&
                Objects.equals(color, that.color);
    }

    /**
     * Unused getter and setter methods
     */
    public void setCurrentCP(int currentCP) {
        this.currentCP = currentCP;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}