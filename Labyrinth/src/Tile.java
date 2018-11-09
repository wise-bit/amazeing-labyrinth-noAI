/*
 * Author: Satrajit Chatterjee
 */

import java.util.Random;

public class Tile {

    Random rand;

    private String name;
    private int rotation = 0; // rotation 0,1,2,3
    private char shape; // the layout of each Tile
    private boolean moveable;
    private int row;
    private int column;

    public Tile(String name, boolean moveable, char shape, int row, int column, int rotation){

        setName(name);
        setMoveable(moveable);
        setShape(shape);
        setRow(row);
        setColumn(column);
        setRotation(rotation);

    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        else if (rotation == 4)
            this.rotation = rand.nextInt(4);
        else
            this.rotation = 0;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "name='" + name + '\'' +
                ", rotation=" + rotation +
                ", shape=" + shape +
                ", moveable=" + moveable +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
