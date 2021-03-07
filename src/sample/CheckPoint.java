package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CheckPoint extends Circle {

    //xCoord and yCoord would be used if we do not extend circle
    private int xCoord;
    private int yCoord;
    private String name;

    public CheckPoint(int xCoord, int yCoord, String name) {
        setCenterX(xCoord);
        setCenterY(yCoord);
        setRadius(30);
        setFill(Color.ORANGE);
        this.name = name;
    }

    @Override
    public String toString() {
        return "CheckPoint:" + "Name: "+name+" xCoord: "+getCenterX()+" yCoord: "+getCenterY();
    }


    //Not used getters and setters

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
