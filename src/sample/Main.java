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
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        //Call menu to get number of cars and create array for that number of cars
        int numberOfCars = StartMenu.firstDisplay();
        Car[] cars = new Car[numberOfCars];

        //For every car allow user to set it up
        for (int i = 0; i < numberOfCars; i++) {
            Car car = StartMenu.secondDisplay();
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
        RaceTrack track = new RaceTrack();
        track.placeCarsOnTrack(cars);
        track.setLines(list[0]);
        list[0].addAll(track.getCheckPoints());

        //Add all cars
        for (int i = 0; i < cars.length; i++) {
            list[0].add(cars[i]);
        }

        //Get slowest and calculate number of "moves" needed to complete track for slowest car
        int slowest = track.getSlowestSpeed();
        int moves = track.getLength()/slowest;

        Button startOverButton = new Button("Restart Race");          //Star over button
        startOverButton.setLayoutX(100);
        startOverButton.setLayoutY(550);

        //If start over is pressed the whole program is restarted
        startOverButton.setOnAction(e -> {
            try {
                start(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        list[0].add(startOverButton);

        //Move cars ever 50 milli seconds
        Timeline move = new Timeline(new KeyFrame(Duration.millis(50),
                        e -> list[0] = track.moveCars(list[0])));

        //Move cars every 50 milli seconds enough times for car cars to make all "moves" around track to finish
        move.setCycleCount(moves);
        move.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
