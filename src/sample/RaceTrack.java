package sample;

import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class RaceTrack {

    //List of cars in track
    private ArrayList<Car> cars = new ArrayList<Car>();

    //List of check points
    private ArrayList<CheckPoint> checkPoints = new ArrayList<CheckPoint>();

    //Length of track used to calculate when car finishes
    private int length;

    //Still unused attributes
    private String name;

    private int carCount;

    private Car prevCar;

    private int moves;

    private int height = 100;

    //Default constructor, default length of track.
    public RaceTrack() {
        length = 1200;
        carCount = 0;
        prevCar = new Car();
    }

    public void placeCarsOnTrack(Car[] carArr) {
        for (int i = 0; i < carArr.length; i++) {
            setUpRoutes(carArr[i], i);
            cars.add(carArr[i]);
        }
        cars.forEach(car -> car.startTime());
        calcSlowestSpeed();
    }

    public void addCheckPoints(){
        CheckPoint cp1 = new CheckPoint( 100, 100, "A");
        CheckPoint cp2 = new CheckPoint( 500, 100, "B");
        CheckPoint cp3 = new CheckPoint( 500, 500, "C");
        CheckPoint cp4 = new CheckPoint( 100, 500, "D");
        checkPoints.add(cp1);
        checkPoints.add(cp2);
        checkPoints.add(cp3);
        checkPoints.add(cp4);
    }

    // Get speed of the slowest car so simulator can run only for as long as needed for slowest car to finish
    public int calcSlowestSpeed(){
        int slowest = 1000;
        for(int i = 0; i < cars.size(); i++){
            if(cars.get(i).getSpeed() < slowest)
                slowest = cars.get(i).getSpeed();
        }
        moves = getLength()/slowest;
        return slowest;
    }

    public int getMoves() {
        return moves;
    }

    //Sets up the cars routes(In order of how they are going to pass them A, B, C, D or D, A, B, C etc.)
    private void setUpRoutes(Car car, int index){
        car.setOrientation(index + 1);
        car.setSizes();
        ArrayList<CheckPoint> path = new ArrayList<CheckPoint>(); //List to add checkpoints
        while(index != 4) {                      //Add all in order
            path.add(checkPoints.get(index));
            index++;
        }
        if(path.size() != 4) {                   //If got to last start with the first until getting all 4
            index = 0;
            while(path.size() != 4) {
                path.add(checkPoints.get(index));
                index++;
            }
        }
        car.setRoute(path);

        car.setX(path.get(0).getCenterX());
        car.setY(path.get(0).getCenterY());
        //Set up route for car
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
            move(cars.get(i), list);                                              //Move car
            list.add(cars.get(i));                                          //Add car to visible list
        }
        return list;                                                        //Return updated list
    }

    public boolean move(Car car, ObservableList list){
        CheckPoint nextCP = car.getRoute().get(car.getCurrentCP()+1);
        int nextXPos = (int)nextCP.getCenterX();                          //x position of next checkpoint
        int nextYPos = (int)nextCP.getCenterY();                          //y position of next checkpoint
        int dif = 0;
        int remainder = 0;
        int orientation = car.getOrientation();
        int curCarX = (int) car.getX();
        int curCarY = (int) car.getY();
        int speed = car.getSpeed();
        car.setOdometer(car.getOdometer()+speed);
        if (orientation == 1) {                         //Car pointing right
            if(curCarX + speed >= nextXPos){             //If moving will exceed checkpoint turn
                dif = nextXPos - curCarX;               //Find when will it exceed
                remainder = speed - dif;                //Calculate remaining distance to travel
                car.setX(curCarX + dif);          //Move car to x value checkpoint
                if(checkNotFinishing(car)) {
                    car.setY(curCarY + remainder);    //Move car the rest of distance
                    car.rotate();
                }
                car.setOrientation(2);                  //Set up new orientation
                car.incrementCP();
            }
            else {
                car.setX(curCarX + speed);
            }
        }
        else if (orientation == 2) {                    //Car pointing down
            if(curCarY + speed > nextYPos){             //If moving will exceed checkpoint turn
                dif = nextYPos - curCarY;               //Find distance it will exceed
                remainder = speed - dif;                //Calculate remaining distance to travel
                car.setY(curCarY + dif);          //
                if(checkNotFinishing(car)) {
                    car.setX(curCarX - remainder);
                    car.rotate();
                }
                car.setOrientation(3);
                car.incrementCP();
            }
            else
                car.setY(curCarY + speed);

        }
        else if (orientation == 3) {                     //Driving left
            if(curCarX - speed <= nextXPos){             //If moving will exceed checkpoint turn
                dif = nextXPos - curCarX;               //Find when will it exceed
                dif = dif*(-1);
                remainder = speed - dif;                //Calculate remaining distance to travel
                car.setX(curCarX - dif);          //Move car to x value checkpoint
                if(checkNotFinishing(car)) {
                    car.setY(curCarY - remainder);    //Move car the rest of distance
                    car.rotate();
                }
                car.setOrientation(4);                  //Set up new orientation
                car.incrementCP();
            }
            else
                car.setX(curCarX - speed);
        }
        else if (orientation == 4) {                        //Traveling up
            if(curCarY - speed <= nextYPos){             //If moving will exceed checkpoint turn
                dif = nextYPos - curCarY;               //Find distance it will exceed
                dif = dif*(-1);
                remainder = speed - dif;                //Calculate remaining distance to travel
                car.setY(curCarY - dif);          //
                if(checkNotFinishing(car)){
                    car.setX(curCarX + remainder);
                    car.rotate();
                }
                car.setOrientation(1);
                car.incrementCP();
            }
            else
                car.setY(curCarY - speed);
        }
        checkForFinish(car, list);
        return true;
    }

    private boolean checkNotFinishing(Car car){
        if(car.getCurrentCP()+1 == 3){
            return false;
        }
        return true;
    }
    //Check if car finished. If odometer >=  length turn car off
    private boolean checkForFinish(Car car, ObservableList list) {
        if(car.getOdometer() >= length) {
            System.out.println(carCount);
            car.setActive(false);
            car.endTime();
            //resultsDisplay();

            if (car.getTime() != prevCar.getTime()) {
                carCount++;
            }
            //System.out.println(carCount);

            car.setCarStats(carCount);

            Text result = new Text(550, height, car.getCarStats());

            height += 50;
            list.add(result);

            prevCar = car;

            return true;
        }
        return false;
    }



    //Moves car depending on orientation


    public void setLines(ObservableList list){
        placeRoad(checkPoints.get(0), checkPoints.get(1), list);
        placeRoad(checkPoints.get(1), checkPoints.get(2), list);
        placeRoad(checkPoints.get(2), checkPoints.get(3), list);
        placeRoad(checkPoints.get(3), checkPoints.get(0), list);

    }

    private void placeRoad(CheckPoint start, CheckPoint end, ObservableList list) {
        Line line = new Line();
        line.setStrokeWidth(30);
        line.setStroke(Color.LIGHTGRAY);
        line.setStartX(start.getCenterX());
        line.setEndX(end.getCenterX());
        line.setStartY(start.getCenterY());
        line.setEndY(end.getCenterY());
        list.add(line);
    }

    /*public void resultsDisplay(){

        Collections.sort(cars);

        for(int i = 0; i < cars.size(); i++) {
            if((i < cars.size() - 1) && (cars.get(i).getTime() < cars.get(i + 1).getTime())){
                cars.get(i).setPlace(i + 1);
            }
            else if((i < cars.size() - 1) && (cars.get(i).getTime() == cars.get(i + 1).getTime())){
                cars.get(i).setPlace(i + 1);
                cars.get(i + 1).setPlace(i + 1);
            }
            else if(i == cars.size() - 1){
                cars.get(i).setPlace(i + 1);
            }
        }

        for(int i = 0; i < cars.size(); i++){

            if(cars.get(i).getPlace() == 1) {

                cars.get(i).setCarStats(cars.get(i).toString() + " finished 1st with a time of "
                        + cars.get(0).getTime() + " seconds");
            }
            if(cars.get(i).getPlace() == 2) {

                cars.get(i).setCarStats(cars.get(i).toString() + " finished 2nd with a time of "
                        + cars.get(0).getTime() + " seconds");
            }
            if(cars.get(i).getPlace() == 3) {

                cars.get(i).setCarStats(cars.get(i).toString() + " finished 3rd with a time of "
                        + cars.get(0).getTime() + " seconds");
            }
            if(cars.get(i).getPlace() == 4) {

                cars.get(i).setCarStats(cars.get(i).toString() + " finished 4th with a time of "
                        + cars.get(0).getTime() + " seconds");
            }
        }
        
    }*/

    //Get length of track
    public int getLength() {
        return length;
    }



    //Getters and setters not used

    public void setLength(int length) {
        this.length = length;
    }


    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public ArrayList<CheckPoint> getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(ArrayList<CheckPoint> checkPoints) {
        this.checkPoints = checkPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}