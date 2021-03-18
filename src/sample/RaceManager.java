package sample;

import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.ArrayList;

/**
 * RaceManager handles the logic of the race. It manages how car moves and when they finish the race.
 */
public class RaceManager {

    //List of cars in track
    private ArrayList<Car> cars = new ArrayList<Car>();

    //List of check points
    private ArrayList<CheckPoint> checkPoints = new ArrayList<CheckPoint>();

    //Length of track used to calculate when car finishes
    private int length;

    //Keeps track of the place cars arrive to the finish checkpoint
    private int placeCount;

    //Keeps track of the previous car that arrived to the finish checkpoint
    private Car prevCar;

    //Holds the number of moves needed for the slowest car to finish the race
    private int moves;

    //Variable to keep track where messages are displayed
    private int height = 100;

    /**
     * Default constructor: Constructs a default race track
     */
    public RaceManager() {
        length = 1200;
        placeCount = 0;
        prevCar = new Car();
    }

    /**
     * Parameter constructor
     * @param cars the cars that will be on the track
     * @param length the length of the track
     * @param moves the number of moves needed for slowest car to finish
     */
    public RaceManager(ArrayList<Car> cars, int length, int moves) {
        this.cars = cars;
        this.length = length;
        this.moves = moves;
        placeCount = 0;
        prevCar = new Car();
    }

    /**
     * Method places cars i track after they are configured by user and sets up their routes.
     * It also starts the timer for each car (Starts race), and calculates slowest speed.
     * Calls helper {@link #setUpRoutes(Car, int)} to set up each car's route
     * @param carArr the array containing all the cars
     */
    public void placeCarsOnTrack(Car[] carArr) {
        for (int i = 0; i < carArr.length; i++) {      //For all cars
            setUpRoutes(carArr[i], i);                 //Set up route
            carArr[i].startTime();                     //Start counting time
            cars.add(carArr[i]);                       //Add to list
        }
        calcSlowestSpeed();                             //Calculate slowest time
    }

    /**
     * Methods creates and adds the checkpoints to the track
     */
    public void addCheckPoints(){
        checkPoints.add(new CheckPoint( 100, 100, "A"));
        checkPoints.add(new CheckPoint( 500, 100, "B"));
        checkPoints.add(new CheckPoint( 500, 500, "C"));
        checkPoints.add(new CheckPoint( 100, 500, "D"));
    }

    /**
     * Methods calculates the speed of the slowest car and calculates the number of moves needed
     * to finish race.
     */
    private void calcSlowestSpeed(){
        int slowest = 1000;
        for (Car car : cars) {
            if (car.getSpeed() < slowest)
                slowest = car.getSpeed();
        }
        moves = getLength()/slowest;
    }

    /**
     * Sets up the cars routes(In order of how they are going to pass them A, B, C, D or D, A, B, C etc.)
     * Sets up initial orientation of cars
     * @param car the car to set the route to
     * @param carNumber the number of the car that is being set up
     */
    private void setUpRoutes(Car car, int carNumber){
        car.setOrientation(carNumber + 1);                          //Set up orientation of car
        car.setSizes();                                             //Set car to point in correct direction
        ArrayList<CheckPoint> path = new ArrayList<CheckPoint>();   //List to add checkpoints
        while(carNumber != 4) {                                     //Add all in order
            path.add(checkPoints.get(carNumber));
            carNumber++;
        }
        if(path.size() != 4) {                         //If last start with the first until getting all 4
            carNumber = 0;
            while(path.size() != 4) {
                path.add(checkPoints.get(carNumber));
                carNumber++;
            }
        }
        car.setRoute(path);                             //Set route car will take
        car.setX(path.get(0).getCenterX());             //Place car in starting checkpoint
        car.setY(path.get(0).getCenterY());
    }

    /**
     * Method is called to simulate the movement of all cars. Every time it is called it will
     * delete the previous old car position and add the car to its new position to simulate movement.
     * Calls helper {@link #moveCar(Car, ObservableList)} to deal with logic of movement
     * @param list the list of visual objects to add car to
     * @return the updated list with new positions
     */
    public ObservableList moveCars (ObservableList list){
        cars.forEach(car -> list.remove(car));                      //Deleting old car positions
        for (Car car : cars) {
            moveCar(car, list);                                     //Move car
            list.add(car);                                          //Add car to visible list
        }
        return list;                                                //Return updated list
    }

    /**
     * Method handles the movement of the car if car is active.
     * Depending on car position calls helper methods moveRight, moveLeft, moveUp or moveDown
     * to move car in that direction.
     * Helper methods will determine if the car is exceeding the next checkpoint and if so,
     * turn the car and call the next helper method to continue rest of the movement.
     * Calls method {@link #checkForFinish(Car, ObservableList)} to display result if car finished,
     * and deactivate car in case race is completed by given car.
     * @param car the car to move
     * @param list the list of visible objects
     * @return true if car moved, false otherwise
     */
    private boolean moveCar(Car car, ObservableList list) {
        if (car.isActive()) {                           //Only move if car is active
            int speed = car.getSpeed();
            int orientation = car.getOrientation();
            if (orientation == 1) {                     //Call move right
                moveRight(car, speed);
            } else if (orientation == 2) {              //Call move down
                moveDown(car, speed);
            } else if (orientation == 3) {              //Call move left
                moveLeft(car, speed);
            } else if (orientation == 4) {              //Call move up
                moveUp(car, speed);
            }
            checkForFinish(car, list);                  //Check if car finished raced
            return true;                                //Car moved
        }
        return false;                                   //Car did not move(it was not active)
    }


    /**
     * Method moves a car to the right, it considers three cases:
     * 1) If car is moved car will not exceed next checkpoint. ACTION: Move car right only
     * 2) Car would exceed next checkpoint, and will not complete the race. ACTION: Move car to next checkpoint
     *      rotate it, set new orientation, and move the car in the new direction whatever distance is left
     * 3) Car would exceed next checkpoint, but at that time race would be complete. ACTION: Move car
     *      to next checkpoint.
     * Upon moving cars update odometer so cars keep track of the distance traveled.
     * @param car the car to move
     * @param howMuch the distance to move the car
     */
    private void moveRight(Car car, int howMuch){   
        CheckPoint nextCP = car.getRoute().get(car.getCurrentCP() + 1);     //Next checkpoint
        int nextXPos = (int) nextCP.getCenterX();                           //x position of next checkpoint
        int curCarX = (int)car.getX();                                      //Current car x position
        
        //Case 1: Car will not exceed next checkpoint when moving
        if(curCarX+howMuch <= nextXPos) {
            car.setX(curCarX + howMuch);
            car.updateOdometer(howMuch);
        }

        //Case 2: Car will exceed next checkpoint when moving, and will not complete race
        else if(curCarX+howMuch > nextXPos && car.getOdometer()+howMuch <= length){
            int dif = nextXPos - curCarX;
            int remainder = howMuch - dif;
            car.setX(curCarX + dif);
            car.incrementCP();
            car.setOrientation(2);
            car.rotate();
            car.updateOdometer(dif);
            moveDown(car, remainder);
        }

        //Case 3: Car will be arriving to last checkpoint
        else if(curCarX+howMuch > nextXPos  && car.getOdometer()+howMuch > length){
            int dif = nextXPos - curCarX;
            car.setX(curCarX + dif);
            car.updateOdometer(dif);
        }

    }

    /**
     * Method moves a car down, it considers three cases:
     * 1) If car is moved car will not exceed next checkpoint. ACTION: Move car down only
     * 2) Car would exceed next checkpoint, and will not complete the race. ACTION: Move car to next checkpoint
     *      rotate it, set new orientation, and move the car in the new direction whatever distance is left
     * 3) Car would exceed next checkpoint, but at that time race would be complete. ACTION: Move car
     *      to next checkpoint.
     * Upon moving cars update odometer so cars keep track of the distance traveled.
     * @param car the car to move
     * @param howMuch the distance to move the car
     */
    private void moveDown(Car car, int howMuch){
        CheckPoint nextCP = car.getRoute().get(car.getCurrentCP() + 1);    //Next checkpoint
        int nextYPos = (int) nextCP.getCenterY();                          //Y position of next checkpoint
        int curCarY = (int)car.getY();                                     //current y position of car

        //Case 1: Car will not exceed next checkpoint when moving
        if(curCarY+howMuch <= nextYPos) {
            car.setY(curCarY + howMuch);
            car.updateOdometer(+howMuch);
        }

        //Case 2: Car will exceed next checkpoint when moving but will not complete race
        else if(curCarY+howMuch > nextYPos && car.getOdometer()+howMuch <= length){
            int dif = nextYPos - curCarY;
            int remainder = howMuch - dif;
            car.setY(curCarY + dif);
            car.incrementCP();
            car.rotate();
            car.updateOdometer(+dif);
            car.setOrientation(3);
            moveLeft(car, remainder);
        }

        //Case 3: Car will be arriving to last checkpoint
        else if(curCarY+howMuch > nextYPos  && car.getOdometer()+howMuch > length){
            int dif = nextYPos - curCarY;
            car.setY(curCarY + dif);
            car.updateOdometer(+dif);
        }
    }

    /**
     * Method moves a car to the left, it considers three cases:
     * 1) If car is moved car will not exceed next checkpoint. ACTION: Move car left only
     * 2) Car would exceed next checkpoint, and will not complete the race. ACTION: Move car to next checkpoint
     *      rotate it, set new orientation, and move the car in the new direction whatever distance is left
     * 3) Car would exceed next checkpoint, but at that time race would be complete. ACTION: Move car
     *      to next checkpoint.
     * Upon moving cars update odometer so cars keep track of the distance traveled.
     * @param car the car to move
     * @param howMuch the distance to move the car
     */
    private void moveLeft(Car car, int howMuch){
        CheckPoint nextCP = car.getRoute().get(car.getCurrentCP() + 1);    //Next checkpoint
        int nextXPos = (int) nextCP.getCenterX();                          //X position of next checkpoint
        int curCarX = (int)car.getX();                                     //Current x position of car

        //Case 1: Car will not exceed next checkpoint when moving
        if(curCarX+howMuch >= nextXPos) {
            car.setX(curCarX - howMuch);
            car.updateOdometer(+howMuch);
        }

        //Case 2: Car will exceed next checkpoint when moving but will not complete race
        else if(curCarX+howMuch < nextXPos && car.getOdometer()+howMuch <= length){
            int dif = curCarX - nextXPos;
            int remainder = howMuch - dif;
            car.setX(curCarX - dif);
            car.incrementCP();
            car.rotate();
            car.updateOdometer(+dif);
            car.setOrientation(4);
            moveUp(car, remainder);
        }

        //Case 3: Car will be arriving to last checkpoint
        else if(curCarX+howMuch < nextXPos  && car.getOdometer()+howMuch > length){
            int dif = curCarX - nextXPos;
            car.setX(curCarX - dif);
            car.updateOdometer(+dif);
        }

    }

    /**
     * Method moves a car up, it considers three cases:
     * 1) If car is moved car will not exceed next checkpoint. ACTION: Move car up only
     * 2) Car would exceed next checkpoint, and will not complete the race. ACTION: Move car to next checkpoint
     *      rotate it, set new orientation, and move the car in the new direction whatever distance is left
     * 3) Car would exceed next checkpoint, but at that time race would be complete. ACTION: Move car
     *      to next checkpoint.
     * Upon moving cars update odometer so cars keep track of the distance traveled.
     * @param car the car to move
     * @param howMuch the distance to move the car
     */
    private void moveUp(Car car, int howMuch){
        CheckPoint nextCP = car.getRoute().get(car.getCurrentCP() + 1);    //Next checkpoint
        int nextYPos = (int) nextCP.getCenterY();                          //Y position of next checkpoint
        int curCarY = (int)car.getY();                                     //Current y position of car

        //Case 1: Car will not exceed next checkpoint when moving
        if(curCarY+howMuch >= nextYPos) {
            car.setY(curCarY - howMuch);
            car.updateOdometer(+howMuch);
        }

        //Case 2: Car will exceed next checkpoint when moving but will not complete race
        else if(curCarY+howMuch < nextYPos && car.getOdometer()+howMuch <= length){
            int dif = curCarY - nextYPos;
            int remainder = howMuch - dif;
            car.setY(curCarY - dif);
            car.incrementCP();
            car.rotate();
            car.updateOdometer(+dif);
            car.setOrientation(1);
            moveRight(car, remainder);
        }

        //Case 3: Car will be arriving to last checkpoint
        else if(curCarY+howMuch > nextYPos  && car.getOdometer()+howMuch > length){
            int dif = curCarY - nextYPos;
            car.setY(curCarY - dif);
            car.updateOdometer(+dif);
        }
    }

    /**
     * Method checks if a car finished the race. It checks the odometer of the car to see if it
     * traveled through all checkpoints.
     * If car completed race it deactivates the car, ends the time count, gets its finishing position and
     * adds the car result information to the visible list.
     * @param car the car to check for completion
     * @param list the list to add the car result information
     * @return true if car finished race, false otherwise
     */
    //Check if car finished. If odometer >=  length turn car off
    private boolean checkForFinish(Car car, ObservableList list) {
        if(car.getOdometer() >= length) {                   //If car finished
            car.setActive(false);                           //Deactivate car
            car.endTime();                                  //End time count
            if (car.getTime() != prevCar.getTime())         //Update its place in the results
                placeCount++;
            car.setCarStats(placeCount);                    //Set the car information based on place
            Text result = new Text(550, height, car.getCarStats()); //Create and place message
            height += 50;
            list.add(result);                               //Add message to visible objects
            prevCar = car;                                  //Update prev car to this so next cars can count place
            return true;
        }
        return false;
    }

    /**
     * Methods places and creates lines in between checkpoints that represent roads.
     * calls helper method {@link #placeRoad(CheckPoint, CheckPoint, ObservableList)} to create roads
     * @param list the list of visible objects to add the lines to
     */
    public void setLines(ObservableList list){
        placeRoad(checkPoints.get(0), checkPoints.get(1), list);
        placeRoad(checkPoints.get(1), checkPoints.get(2), list);
        placeRoad(checkPoints.get(2), checkPoints.get(3), list);
        placeRoad(checkPoints.get(3), checkPoints.get(0), list);
    }

    /**
     *
     * @param start the checkpoint where line starts
     * @param end the checkpoint where line ends
     * @param list the list of visible objects to add the lines to
     */
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


    //toString and equals do not make sense for this class so they are not included.

    //Getters and setters

    /**
     * Gets the number of moves needed to finish race
     * @return the minimum number of moves for last car to finish race
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Sets the number of moves needed to finish race
     * @param moves the number to set
     */
    public void setMoves(int moves) {
        this.moves =  moves;
    }

    /**
     * Gets the length of the track
     * @return the length of the track
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the length of the track
     * @param length the length of the track
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets the list of cars
     * @return the list of cars
     */
    public ArrayList<Car> getCars() {
        return cars;
    }

    /**
     * Sets the list of cars
     * @param cars the list of cars
     */
    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    /**
     * Gets the list of check points
     * @return the list check points
     */
    public ArrayList<CheckPoint> getCheckPoints() {
        return checkPoints;
    }

    /**
     * Sets the list of check points
     * @param checkPoints  the list check points
     */
    public void setCheckPoints(ArrayList<CheckPoint> checkPoints) {
        this.checkPoints = checkPoints;
    }

    /**
     * Gets the place count
     * @return the place count
     */
    public int getPlaceCount() {
        return placeCount;
    }

    /**
     * Sets the place count
     * @param placeCount the place count
     */
    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    /**
     * Gets the height of the message to display
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the message to display
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the previous car that finished race
     * @return previous finished car
     */
    public Car getPrevCar() {
        return prevCar;
    }

    /**
     * Sets the previous car that finished race
     * @param prevCar the car to set
     */
    public void setPrevCar(Car prevCar) {
        this.prevCar = prevCar;
    }
}