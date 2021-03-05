package sample;

import java.awt.*;

public class Car {


    private Engine engine;
    private Tires tires;
    private Transmission transmission;
    private Color color;
    private double speed;
    private int checkpointNumber;
    private double time;
    private String name;
    private boolean finished;
    private int direction;

    public Car() {
        //FIX
    }

    public Car(Engine engine, Tires tires, Transmission transmission,
               Color color, int checkpointNumber,
               double time, String name) {
        this.engine = engine;
        this.tires = tires;
        this.transmission = transmission;
        this.color = color;
        this.checkpointNumber = checkpointNumber;
        this.time = time;
        this.name = name;
        finished = false;

    }

    private void setSpeed() {
        // Calc speed based on components
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

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCheckpointNumber() {
        return checkpointNumber;
    }

    public void setCheckpointNumber(int checkpointNumber) {
        this.checkpointNumber = checkpointNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int checkpointNumber) {
        this.direction = direction;
    }

    public double getDistanceTraveled(double time) {
        return 0;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", tires=" + tires +
                ", transmission=" + transmission +
                ", color=" + color +
                ", speed=" + speed +
                '}';
    }
}
