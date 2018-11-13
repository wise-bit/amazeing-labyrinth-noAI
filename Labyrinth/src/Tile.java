/**
 * Author: Satrajit Chatterjee
 *
 * This class includes all methods dealing with tiles. This includes:
 * Name, Rotation, Shape, isMoveable, row number, column number
 * Also includes layout in a 2D array format of a matrix of 1s and 0s used to generate maze backend
 * Also includes a method which can rotate these tiles, and their matrices
 *
 */

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tile extends JLabel{

    Random rand = new Random();

    private String name;
    private int rotation = 0; // rotation 0,1,2,3
    private char shape; // the layout of each Tile
    private boolean moveable;
    private int row;
    private int column;
    private int[][] layout;

    // Constructor which initializes all of the attributes when a new Tile is made
    public Tile(String name, boolean moveable, char shape, int row, int column, int rotation){

        setName(name);
        setMoveable(moveable);
        setShape(shape);
        setRow(row);
        setColumn(column);
        setRotation(rotation); // sets the rotation count
        setLayout(); // Set the initial layout
        orientation(); // Makes sure the tile is rotated n number of times as required

    }

    // Checks if the tile is moveable
    public boolean isMoveable() {
        return moveable;
    }

    // Sets the moveable attribute
    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    // Returns the row number of a tile
    public int getRow() {
        return row;
    }

    // Sets row number of a tile
    public void setRow(int row) {
        this.row = row;
    }

    // Returns the column number of a tile
    public int getColumn() {
        return column;
    }

    // Sets column number of a tile
    public void setColumn(int column) {
        this.column = column;
    }

    // Returns name of the tile
    public String getName() {
        return name;
    }

    // Sets name of the tile
    public void setName(String name) {
        this.name = name;
    }

    // Returns shape of the tile: t, l, or i
    public char getShape() {
        return shape;
    }

    // Sets shape of the tile: t, l, or i
    public void setShape(char shape) {
        this.shape = shape;
    }

    // Returns rotation of the tile
    public int getRotation() {
        return rotation;
    }

    // Error checks rotation, between 0 and 3
    public void setRotation(int rotation) {
        if (rotation >= 0 && rotation <= 3)
            this.rotation = rotation;
        else if (rotation == 4)
            // When 4 is passed, random is assigned
            this.rotation = rand.nextInt(4);
        else
            this.rotation = 0;
    }

    // Sets a layout for the tiles
    public void setLayout(){

        // If shape is in the form of an 'I'
        if (getShape() == 'i') {
            layout = new int[][]{
                    {0,1,0},
                    {0,1,0},
                    {0,1,0},
            };
        } // If shape is in the form of an 'L'
        else if (getShape() == 'l') {
            layout = new int[][]{
                    {0,1,0},
                    {0,1,1},
                    {0,0,0},
            };
        } // If shape is in the form of a 'T'
        else if (getShape() == 't') {
            layout = new int[][]{
                    {0,0,0},
                    {1,1,1},
                    {0,1,0},
            };
        }
        this.setIcon(new ImageIcon(makeFileName()));


    }

    // Returns layout as a 2D array of 0s and 1s
    public int[][] getIntLayout() {
        return layout;
    }

    // Rotates the tile 90 degrees clockwise
    public void rotate(){

        int N = getIntLayout()[0].length;
        for (int x = 0; x < N / 2; x++)
        {
            for (int y = x; y < N-x-1; y++)
            {
                /// Stored for later modification or for debugging
                // left: layout[N-1-y][x]
                // top: layout[x][y]
                // right: layout[y][N-1-x]
                // bottom: layout[N-1-x][N-1-y]

                // store current cell in temp variable
                int temp = layout[x][y]; // store current cell (left) in temp variable
                layout[x][y] = layout[N-1-y][x]; // move values from left to top
                layout[N-1-y][x] = layout[N-1-x][N-1-y]; // move values from bottom to left
                layout[N-1-x][N-1-y] = layout[y][N-1-x]; // move values from right to bottom
                layout[y][N-1-x] = temp; // assign temp to right
            }
        }

    }

    // Rotates the tile n number of times as governed by getRotation()
    public void orientation(){

        // Repeats the rotation process getRotation() number of times
        for (int i = 0; i < getRotation(); i++){
            rotate();
        }

    }

    // Generates file name of tile image ofr GUI
    public String makeFileName(){

        // If the tile is null
        if (this == null)
            return null;

        // If it is one of the empty ones, return shape and rotation followed by filetype
        else if (getName().equals("Empty"))
            return Character.toUpperCase(getShape()) + Integer.toString(getRotation()) + ".png";
        	
        // If the tile has a special name (treasure)
        else
            return getName().replaceAll("[^a-zA-Z:]", "") + getRotation() + ".png";
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

    public String toStorageString() {
        return name + "," + moveable + "," + shape + "," + row + "," + column + "," + rotation;
    }

}
