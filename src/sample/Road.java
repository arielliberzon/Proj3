package sample;

import javafx.scene.shape.Line;

//Just a line in between checkpoints. Maybe just use line?
public class Road extends Line {

    //Checkpoint where line originates
    private CheckPoint start;

    //Checkpoint wher line ends
    private CheckPoint end;

    //Constructor
    public Road(CheckPoint start, CheckPoint end) {
        this.start = start;
        this.end = end;
        setStartX(start.getCenterX());
        setEndX(end.getCenterX());
        setStartY(start.getCenterY());
        setEndY(end.getCenterY());
    }


    //Not used getters and setters

    public CheckPoint getStart() {
        return start;
    }

    public void setStart(CheckPoint start) {
        this.start = start;
    }

    public CheckPoint getEnd() {
        return end;
    }

    public void setEnd(CheckPoint end) {
        this.end = end;
    }
}
