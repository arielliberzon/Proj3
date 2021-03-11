package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class StartMenu {

    private static int numCars;

    private static int playerCount = 1;

    private static Car car;

    public static int firstDisplay(){

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        ChoiceBox<Integer> numChoice = new ChoiceBox<Integer>();
        numChoice.getItems().addAll(1,2,3,4);

        Button nextButton = new Button("Next") ;
        nextButton.setPrefSize(100, 50);
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18);
        Label title = new Label("Select Number of Cars:");
        title.setFont(font);

        numChoice.setOnAction(e -> numCars = numChoice.getValue());
        nextButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, numChoice, nextButton);
        layout.setAlignment(Pos.CENTER);

        Scene introScene = new Scene(layout, 300, 200);
        stage.setScene(introScene);
        stage.showAndWait();

        return numCars;
    }

    public static Car secondDisplay() {

        String model = "Car #" + playerCount;

        String[] options = new String[4];

        String[] engineTypes = new String[] {"Engine 1", "Engine 2", "Engine 3", "Engine 4"};
        String[] tireTypes = new String[] {"Tire 1", "Tire 2", "Tire 3", "Tire 4"};
        String[] transmissionTypes = new String[] {"Transmission 1", "Transmission 2",
        "Transmission 3", "Transmission 4"};
        String[] colorTypes = new String[] {"Blue", "Red", "Green", "Yellow"};

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        ChoiceBox<String> engineChoice = new ChoiceBox<String>();
        ChoiceBox<String> tireChoice = new ChoiceBox<String>();
        ChoiceBox<String> transmissionChoice = new ChoiceBox<String>();
        ChoiceBox<String> colorChoice = new ChoiceBox<String>();

        engineChoice.getItems().addAll(engineTypes[0], engineTypes[1],
                engineTypes[2], engineTypes[3]);
        tireChoice.getItems().addAll(tireTypes[0], tireTypes[1], tireTypes[2], tireTypes[3]);
        transmissionChoice.getItems().addAll(transmissionTypes[0], transmissionTypes[1],
                transmissionTypes[2], transmissionTypes[3]);
        colorChoice.getItems().addAll(colorTypes[0], colorTypes[1], colorTypes[2], colorTypes[3]);

        Button enterButton = new Button("Enter");
        enterButton.setPrefSize(200, 50);

        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18);
        Label title1 = new Label("Select Engine:");
        Label title2 = new Label("Select Tires:");
        Label title3 = new Label("Select Transmission:");
        Label title4 = new Label("Select Color:");
        title1.setFont(font);
        title2.setFont(font);
        title3.setFont(font);
        title4.setFont(font);

        engineChoice.setOnAction(e -> options[0] = engineChoice.getValue());
        tireChoice.setOnAction(e -> options[1] = tireChoice.getValue());
        transmissionChoice.setOnAction(e -> options[2] = transmissionChoice.getValue());
        colorChoice.setOnAction(e -> options[3] = colorChoice.getValue());
        enterButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title1, engineChoice, title2, tireChoice, title3,
                transmissionChoice, title4, colorChoice, enterButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.showAndWait();

        car = createCar(model, options);
        playerCount++;
        return car;
    }

    private static Car createCar(String model, String[] data) {
        Engine engine = new Engine(data[0]);
        Tires tires = new Tires(data[1]);
        Transmission transmission = new Transmission(data[2]);
        Color color = getColor(data[3]);

        Car car = new Car(model, engine, tires, transmission, color);

        return car;
    }

    private static javafx.scene.paint.Color getColor(String data) {
        if (data.equals("Blue"))
            return javafx.scene.paint.Color.BLUE;
        if (data.equals("Red"))
            return javafx.scene.paint.Color.RED;
        if (data.equals("Green"))
            return javafx.scene.paint.Color.GREEN;
        if (data.equals("Yellow"))
            return javafx.scene.paint.Color.YELLOW;
        else
            return javafx.scene.paint.Color.BLACK;
    }
}
