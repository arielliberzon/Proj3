package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        int test = StartMenu.firstDisplay();
        for (int i = 0; i < test; i++) {
            Car car = StartMenu.secondDisplay();
            System.out.println(car.toString());
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
        Car one = new Car(1, cp1.getCenterX(), cp1.getCenterY(), 10, Color.RED, "Red");   //Looking left
        Car two = new Car(2, cp2.getCenterX(), cp2.getCenterY(), 5, Color.GREEN, "Green");   //Looking down
        Car three = new Car(3, cp3.getCenterX(), cp3.getCenterY(), 4, Color.BLACK, "Black");   //Looking right
        Car four = new Car(4, cp4.getCenterX(), cp4.getCenterY(), 8, Color.BROWN, "Brown");   //Looking up
        track.placeCarsOnTrack(one, two, three, four);

        //Add checkpoints
        list[0].addAll(cp1, cp2, cp3, cp4);

        //Add lines in between points
        track.setLines(list[0]);

        //Add cars
        list[0].addAll(one, two, three, four);

        //Get slowest and calculate number of "moves" needed to complete track for slowest car
        int slowest = track.getSlowestSpeed();
        int moves = track.getLength()/slowest;

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
