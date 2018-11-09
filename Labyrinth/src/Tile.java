/*
 * Author: Satrajit Chatterjee
 */

public class Tile {

    private int rotation = 0; // rotation 0,1,2,3
    private char shape; // the layout of each Tile


    public char getShape() {
        return shape;
    }

    public void setShape(char shape) {
        this.shape = shape;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        if (rotation >= 0 && rotation <= 3)
            this.rotation = rotation;
        else
            this.rotation = 0;
    }

    public Tile(char shape){

        setRotation(0);
        setShape(shape);

    }
    /*
    orientation
    ownership
    row
    column
    moveable
     */

}
