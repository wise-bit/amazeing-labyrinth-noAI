/*
 * Author: Satrajit Chatterjee
 */

import java.util.Random;

public class Tile {

    Random rand = new Random();

    private String name;
    private int rotation = 0; // rotation 0,1,2,3
    private char shape; // the layout of each Tile
    private boolean moveable;
    private int row;
    private int column;
    private int[][] layout;

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

    public void setLayout(){
        int[][] layout =  new int[3][3];

        if (getShape() == 'l') {
            layout = new int[][]{
                    {0,1,0},
                    {0,1,0},
                    {0,1,0},
            };
        } else if (getShape() == 'l') {
            layout = new int[][]{
                    {0,1,0},
                    {0,1,1},
                    {0,0,0},
            };
        } else if (getShape() == 't') {
            layout = new int[][]{
                    {0,0,0},
                    {1,1,1},
                    {0,1,0},
            };
        }

    }

    public int[][] getLayout() {
        return layout;
    }

    // Error checks rotation
    public void setRotation(int rotation) {
        if (rotation >= 0 && rotation <= 3)
            this.rotation = rotation;
        // 4 triggers randomizer
        else if (rotation == 4)
            this.rotation = rand.nextInt(4);
        else
            this.rotation = 0;
    }

    // TODO: Add 90 degrees rotation
    public void rotate(){

        for(int i=0; i<getLayout()[0].length; i++){
            for(int j=getLayout().length-1; j>=0; j--){
                layout[i][j] = layout[j][i];
            }
        }

    }

    // Rotates the tile n number of times as governed by getRotation()
    public void orientation(){

        for (int i = 0; i < getRotation(); i++){
            rotate();
        }

    }

    // toString for error monitoring
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
