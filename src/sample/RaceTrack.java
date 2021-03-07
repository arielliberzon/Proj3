package sample;

import javafx.collections.ObservableList;

import java.util.LinkedList;

public class RaceTrack {

    //List of cars in track
    private LinkedList<Vehicle> cars = new LinkedList<Vehicle>();

    //List of check points
    private LinkedList<CheckPoint> checkPoints = new LinkedList<CheckPoint>();

    //Length of track used to calculate when car finishes
    private int length;

    //Still unused attributes
    private String name;

    //Default constructor, default length of track.
    public RaceTrack() {
        length = 1200;
    }

    //Puts the cars in track and calls to set up their routes
    public void placeCarsOnTrack(Vehicle c1, Vehicle c2, Vehicle c3, Vehicle c4){
        setUpRoutes(c1, 0);
        setUpRoutes(c2, 1);
        setUpRoutes(c3, 2);
        setUpRoutes(c4, 3);
        cars.add(c1);
        cars.add(c2);
        cars.add(c3);
        cars.add(c4);
        cars.forEach(car -> car.startTime());
    }

    //Sets up the cars routes(In order of how they are going to pass them A, B, C, D or D, A, B, C etc.)
    private void setUpRoutes(Vehicle car, int index){
        LinkedList<CheckPoint> path = new LinkedList<CheckPoint>(); //List to add checkpoints
        while(index != 4){                      //Add all in order
            path.add(checkPoints.get(index));
            index++;
        }
        if(path.size() != 4){                   //If got to last start with the first until getting all 4
            index = 0;
            while(path.size() != 4){
                path.add(checkPoints.get(index));
                index++;
            }
        }
        car.setRoute(path);                     //Set up route for car
    }

    //Add checkpoints to track
    public void addCheckPoints(CheckPoint cp1, CheckPoint cp2, CheckPoint cp3, CheckPoint cp4){
        checkPoints.add(cp1);
        checkPoints.add(cp2);
        checkPoints.add(cp3);
        checkPoints.add(cp4);
    }

    //Moves cars (Erases old cars' positions and adds them to list of visual objects with new position)
    //So that they are rendered in new position
    public ObservableList moveCars (ObservableList list){
        for(int i = 0; i < cars.size(); i++){                               //Remove cars that finished race
            if(!cars.get(i).isActive())
                cars.remove(cars.get(i));
        }
        cars.forEach(car -> list.remove(car));                              //Deleting old car positions in GUI
        for (int i = 0; i < cars.size(); i++) {
            move(cars.get(i));                                              //Move car
            list.add(cars.get(i));                                          //Add car to visible list
        }
        return list;                                                        //Return updated list
    }

    //Turns car if cars reached checkpoint. Sets new orientation
    private boolean turnCar(Vehicle car){
        int orientation = car.getOrientation();
        double xCur = car.getCenterX();                                 //Current x position of car
        double yCur = car.getCenterY();                                 //Current y position of car
        CheckPoint nextCP = car.getRoute().get(car.getCurrentCP()+1);   //Get next checkpoint
        double nextXPos = nextCP.getCenterX();                          //x position of next checkpoint
        double nextYPos = nextCP.getCenterY();                          //y position of next checkpoint
        if(orientation == 1 && xCur >= nextXPos ) {                     //If facing right
            car.setOrientation(2);
        }
        else if(orientation == 2 && yCur >= nextYPos) {                 //If facing down
            car.setOrientation(3);
        }
        else if(orientation == 3 && xCur <= nextXPos) {                 //If facing left
            car.setOrientation(4);
        }
        else if(orientation == 4 && yCur <= nextYPos) {                 //If facing up
            car.setOrientation(1);
        }
        else
            return true;
        car.incrementCP();
        return true;
    }

    //Moves car depending on orientation
    public void move(Vehicle car){
        turnCar(car);
        int orientation = car.getOrientation();
        double x = car.getCenterX();
        double y = car.getCenterY();
        int speed = car.getSpeed();
        if (orientation == 1) {
            car.setCenterX(x + speed);
        }
        else if (orientation == 2) {
            car.setCenterY(y + speed);
        }
        else if (orientation == 3) {
            car.setCenterX(x - speed);
        }
        else if (orientation == 4) {
            car.setCenterY(y - speed);
        }
        //Update odometer to know when race is finished
        car.setOdometer(car.getOdometer()+speed);
        checkForFinish(car);
    }

    //Check if car finished. If odometer >=  length turn car off
    private boolean checkForFinish(Vehicle car) {
        if(car.getOdometer() >= length) {
            car.setActive(false);
            car.endTime();
            System.out.println(car.toString()+" Just finished with a time of: "+car.getTime() +" seconds");
            return true;
        }
        return false;
    }

    // Get speed of the slowest car so simulator can run only for as long as needed for slowest car to finish
    public int getSlowestSpeed(){
        int slowest = 1000;
        for(int i = 0; i < cars.size(); i++){
            if(cars.get(i).getSpeed() < slowest)
                slowest = cars.get(i).getSpeed();
        }
        return slowest;
    }

    //Get length of track
    public int getLength() {
        return length;
    }



    //Getters and setters not used

    public void setLength(int length) {
        this.length = length;
    }


    public LinkedList<Vehicle> getCars() {
        return cars;
    }

    public void setCars(LinkedList<Vehicle> cars) {
        this.cars = cars;
    }

    public LinkedList<CheckPoint> getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(LinkedList<CheckPoint> checkPoints) {
        this.checkPoints = checkPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

