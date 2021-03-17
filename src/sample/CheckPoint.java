package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Objects;

/**
 * Class checkpoint is a place where cars have to pass through in order to complete a race
 */
public class CheckPoint extends Circle {
    // DELETE THIS LINE

    //xCoord holds the x position of the checkpoint
    private int xCoord;

    //yCoord holds the y position of the checkpoint
    private int yCoord;

    //Holds the name of the checkpoint
    private String name;

    /**
     * Constructs a checkpoint
     * @param xCoord the x position to place the checkpoint at
     * @param yCoord the y position to place the checkpoint at
     * @param name the name to set to the checkpoint
     */
    public CheckPoint(int xCoord, int yCoord, String name) {
        setCenterX(xCoord);
        setCenterY(yCoord);
        setRadius(30);
        setFill(Color.ORANGE);
        this.name = name;
    }

    /**
     * Default constructor. Constructs a default Checkpoint
     */
    public CheckPoint(String name) {
        setCenterX(100);
        setCenterY(100);
        setRadius(30);
        setFill(Color.ORANGE);
        name = "x";
    }

    /**
     * Creates string representation of check point
     * @return a string with information about the checkpoint
     */
    @Override
    public String toString() {
        return "CheckPoint:" + "Name: "+name+" xCoord: "+getCenterX()+" yCoord: "+getCenterY();
    }


    //Getters, setters and equals

    /**
     * Gets the x coordinate of the checkpoint
     * @return the x coordinat of the checkpoint
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * Sets the x coordinate of the checkpoint
     * @param xCoord the y coordinate of the checkpoint
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Gets the y coordinate of the checkpoint
     * @return the y coordinate of the checkpoint
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * Sets the y coordinate of the checkpoint
     * @param yCoord the y coordinate of the checkpoint
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * Gets the name of the checkpoint
     * @return the name of the checkpoint
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the checkpoint
     * @param name the name of the checkpoint
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Overriding equal: Checks whether o == this
     * param o the object to test for equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckPoint)) return false;
        CheckPoint that = (CheckPoint) o;
        return xCoord == that.xCoord &&
                yCoord == that.yCoord &&
                Objects.equals(name, that.name);
    }

}
