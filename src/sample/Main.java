package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {


        int test = StartMenu.firstDisplay();
        Car[] cars = new Car[test];
        for (int i = 0; i < test; i++) {
            Car car = StartMenu.secondDisplay();
            cars[i] = car;
            System.out.println(cars[i].toString()+"!");
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
        CheckPoint cp1 = new CheckPoint( 100, 100, "A");
        CheckPoint cp2 = new CheckPoint( 500, 100, "B");
        CheckPoint cp3 = new CheckPoint( 500, 500, "C");
        CheckPoint cp4 = new CheckPoint( 100, 500, "D");
        track.addCheckPoints(cp1, cp2, cp3, cp4);

        track.placeCarsOnTrack(cars);

        //Add lines in between points
        track.setLines(list[0]);

        //Add checkpoints
        list[0].addAll(cp1, cp2, cp3, cp4);

        //Add cars
        for (int i = 0; i < cars.length; i++) {
            list[0].add(cars[i]);
            System.out.println(cars[i].toString()+"?");
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

    //DELETE THIS COMMENT

    public static void main(String[] args) {
        launch(args);
    }
}
