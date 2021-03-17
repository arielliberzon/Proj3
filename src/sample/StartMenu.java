/**
 *  This is a class used by the JavaFX application (main) to set the number
 *  of cars in the race and then create each car by choosing its attributes
 *  such as engine, color, etc.
 *  If the user hits "Enter" when choosing the number of cars and leaves the
 *  field blank then the number of cars will be set to 1
 *  If the user hits "Enter" when selecting the car's attributes leaves at
 *  LEAST ONE field blank then a car with the will be created using the
 *  default options array
 */
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

public class StartMenu {

    private int numCars;
    private int playerCount;
    private Car car;
    private String[] options = new String[4];

    /**
     * Final values that are used in the display methods
     */
    private final String[] defOpts = new String[] {"Rotary Engine", "All-Season",
            "Automatic Transmission", "Blue"};

    private final String[] engineTypes = new String[] {"Rotary Engine",
            "Diesel Engine", "Gasoline Engine", "Radial Engine"};
    private final String[] tireTypes = new String[] {"All-Season", "Touring",
            "Performance", "Track & Competition"};
    private final String[] transmissionTypes = new String[] {"Automatic Transmission",
            "Manual Transmission", "Automated Manual Transmission",
            "Continuously Variable Transmission"};
    private final String[] colorTypes = new String[] {"Blue", "Red", "Green", "Yellow"};

    /**
     * Default constructor
     */
    public StartMenu() {
        numCars = 1;
        playerCount = 1;
    }

    /**
     * Presents a GUI to select the number of players
     * @return The number of players/cars participating in the race
     */
    public int playerDisplay() {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        ChoiceBox<Integer> numChoice = new ChoiceBox<Integer>();
        numChoice.getItems().addAll(1,2,3,4);

        Button enterButton = new Button("Enter") ;
        enterButton.setPrefSize(100, 50);
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18);
        Label title = new Label("Select Number of Cars:");
        title.setFont(font);

        numChoice.setOnAction(e -> numCars = numChoice.getValue());
        enterButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, numChoice, enterButton);
        layout.setAlignment(Pos.CENTER);

        Scene introScene = new Scene(layout, 300, 200);
        stage.setScene(introScene);
        stage.showAndWait();

        return numCars;
    }

    /**
     * Presents a GUI to create a car based on the selected attributes
     * @return A car object that will added to the race
     */
    public Car carDisplay() {

        String name = "Car #" + playerCount;

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

        for (int i = 0; i < options.length; i++) {
            if (options[i] == null)
                car = createCar(name, defOpts);
            if (options[options.length -1] != null)
                car = createCar(name, options);
        }
        playerCount++;
        return car;
    }

    /**
     * Helper method to create a car based on an array of data
     * @param name String that represents name of the car (ex: Car #1)
     * @param data String array that has names for each attribute (engine, tires, etc.)
     * @return Car object that was created using the attributes listed above
     */
    private Car createCar(String name, String[] data) {
            Engine engine = new Engine(data[0]);
            Tires tires = new Tires(data[1]);
            Transmission transmission = new Transmission(data[2]);
            Color color = getColor(data[3]);
            Car car = new Car(playerCount, name, engine, tires, transmission, color);
        return car;
    }

    /**
     * Helper method to set the color of a car based on a string
     * @param data String array which has the color reference stored in its final index
     * @return The color object that matches the reference
     */
    private javafx.scene.paint.Color getColor(String data) {
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
