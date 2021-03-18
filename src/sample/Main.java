package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main is the class that runs the simulation. The simulation is a car race where cars need to pass through all
 * checkpoints in order to complete the race. User is first allowed to pick the number of cars and configure them.
 * @author S. Hernandez
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        /** Ask user for number of cars
         * @author A. Liberzon
         */
        StartMenu startMenu = new StartMenu();
        int numberOfCars = startMenu.playerDisplay();

        /** Ask user to set cars
         * @author A. Liberzon
         */
        Car[] cars = new Car[numberOfCars];
        for (int i = 0; i < numberOfCars; i++) {
            Car car = startMenu.carDisplay();
            cars[i] = car;
        }

        //Set up GUI
        int width = 1024;
        int height = 768;
        Group root = new Group();
        ObservableList[] list = {root.getChildren()};       //List of visual objects
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Racing");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Set up track and its objects
        RaceManager track = new RaceManager();
        track.addCheckPoints();                     //Add checkpoints to track
        track.placeCarsOnTrack(cars);               //Put cars on track
        track.setLines(list[0]);                    //Add lines to connect checkpoints
        list[0].addAll(track.getCheckPoints());     //Get checkpoints for GUI

        //Add cars
        for (Car car : cars) list[0].add(car);

        //Set up start over button
        Button startOverButton = new Button("New Race");
        startOverButton.setLayoutX(100);
        startOverButton.setLayoutY(550);
        list[0].add(startOverButton);

        //If start over is pressed the whole program is restarted
        startOverButton.setOnAction(e -> {
            try {
                start(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        //Move cars ever 50 milli seconds
        Timeline move = new Timeline(new KeyFrame(Duration.millis(50),
                        e -> list[0] = track.moveCars(list[0])));

        //Move cars every 50 milli seconds enough times for car cars to make all "moves" around track to finish
        move.setCycleCount(track.getMoves());
        move.play();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
