/*
Author: Satrajit Chatterjee
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Setup extends JFrame {

    private Random rand = new Random();

    public static Tile[][] board;
    public static int[][] binary;

    //// Variable initializations


    // A counter to keep track of availability of a spot on the board
    private ArrayList<Integer> counter = new ArrayList<Integer>();

    // Declares the file with all the treasures listed, with all the attributes
    private File text = new File("Labyrinth/res/treasures.txt");

    // Initializes scanner
    Scanner file = new Scanner(text);

    // i and L shaped tiles count from the instructions manual
    private int lCount = 9;
    private int iCount = 13;


    //// Main Methods


    // Constructor which calls the initialization method
    public Setup() throws FileNotFoundException {
    }

    // Returns the counter ArrayList keeping track of empty spaces
    public ArrayList<Integer> getCounter() {
        return counter;
    }

    // Sets counter, kept for debugging purposes mostly
    public void setCounter(ArrayList<Integer> counter) {
        this.counter = counter;
    }


    //// Accessors and Modifiers


    // Returns the board set up by the class using tiles
    public Tile[][] getBoard() {
        return board;
    }

    // Kept for modifying board for debugging purposes
    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    // The contents of the treasure list file
    public File getText() {
        return text;
    }

    // Sets the treasures textfile, method not used in current version
    public void setText(File text) {
        this.text = text;
    }

    // Accesses the Scanner object file
    public Scanner getFile() {
        return file;
    }

    // Modifies the Scanner object file
    public void setFile(Scanner file) {
        this.file = file;
    }


    //// HELPER METHODS (Not all commented)


    // This returns a string array with the names of all treasures
    public ArrayList<String> listOfTreasures() throws FileNotFoundException {

        // Initializes list
        ArrayList<String> list = new ArrayList<String>();

        // Resets file location to beginning
        setFile(new Scanner(text));

        // Adds each name from file to arraylist
        while(getFile().hasNextLine()){
            list.add(getFile().nextLine().split(",")[0]);
        }

        // Shuffles list
        Collections.shuffle(list);
        return list;

    }

    // Initializer, which places the fixed tiles first, followed by the moveable tiles, and finally fills up the rest of the board
    // Goes through all of the lines of the file each time to ensure they are all categorized correctly
    public void init() throws FileNotFoundException {

        board = new Tile[9][9];
        initializeCounter();

        file = new Scanner(text);
        while(getFile().hasNextLine()){
            String line = getFile().nextLine();
            fixed(line);
        }

        file = new Scanner(text);
        while(getFile().hasNextLine()){
            String line = getFile().nextLine();
            otherTreasures(line);
        }

        otherTiles();
        setBinary(fullBinaryBoard());

        System.out.println("New initialization");

    }

    // Places all the fixed tiles
    public void fixed(String attributes){

        // Splits the input from the file into separate attributes for each object listed
        String[] attribs = attributes.split(",");

        // Only place this if it is not moveable (false)
        if (attribs[1].equals("false")) {

            // Fetches row and column, which are only listed in file for fixed files
            int row = Integer.parseInt(attribs[3]);
            int column = Integer.parseInt(attribs[4]);

            // Adds the new Tile object to the respective row and column position, inputting all characteristics
            this.board[row][column] =
                    new Tile(attribs[0], Boolean.parseBoolean(attribs[1]), attribs[2].charAt(0), row, column, Integer.parseInt(attribs[5]));

            // Removes this space from the list of open spaces
            this.counter.remove(new Integer(row * 9 + column));

            // Print line for debugging purposes
            // System.out.println(this.board[row][column]);

        }
    }

    // Place the other treasures
    public void otherTreasures(String attributes){

        // Splits it into the attributes as string array
        String[] attribs = attributes.split(",");

        // Only if the tile is moveable
        if (attribs[1].equals("true")) {


            int row = getCounter().get(0)/9;
            int column = getCounter().get(0)%9;

            if (getBoard()[row][column] == null) {

                this.board[row][column] =
                        new Tile(attribs[0], Boolean.parseBoolean(attribs[1]), attribs[2].charAt(0), row, column, 4);


            } else {
                System.out.println(row + " " + column);
            }

            counter.remove(0);

        }
    }

    // Places the starting position tiles and the empty tiles
    public void otherTiles(){

        for (int i = 1; i < 8; i++) {

            for (int j = 1; j < 8; j++) {

                if (i == 1 && j == 1) this.board[i][j] = new Tile("Start1", false, 'l', i, j, 1);
                else if (i == 1 && j == 7) this.board[i][j] = new Tile("Start2", false, 'l', i, j, 2);
                else if (i == 7 && j == 1) this.board[i][j] = new Tile("Start3", false, 'l', i, j, 0);
                else if (i == 7 && j == 7) this.board[i][j] = new Tile("Start4", false, 'l', i, j, 3);

                else if (getBoard()[i][j] == null) {

                    if (rand.nextBoolean()){
                        if (isIAvailable()){
                            useI();
                            this.board[i][j] = new Tile("Empty", true, 'i', i, j, 4);
                        } else {
                            useL();
                            this.board[i][j] = new Tile("Empty", true, 'l', i, j, 4);
                        }
                    } else {
                        if (isLAvailable()){
                            useL();
                            this.board[i][j] = new Tile("Empty", true, 'l', i, j, 4);
                        } else {
                            useI();
                            this.board[i][j] = new Tile("Empty", true, 'i', i, j, 4);
                        }
                    }

                    counter.remove(new Integer(i*9+j));

                }
            }
        }
    }

    // Initializes the counter array from
    public void initializeCounter(){

        for (int i = 9; i < 72; i++){
            this.counter.add(i);
        }

        // removes all elements which would lead to a column of 0
        int looper = 0;
        while (looper < counter.size()){
            int current = counter.get(looper);
            // remove the four corners from being taken as a tile, and hence later replaced permanently, as well as the sides
            if (current % 9 == 0 || (current + 1) % 9 == 0 || current == 10 || current == 16 || current == 64 || current == 70){
                counter.remove(looper);
            } else {
                looper++;
            }
        }

        // Randomizes the ArrayList to ensure the tiles are placed randomly
        Collections.shuffle(this.counter);

    }

    // Checks if 'i' shaped tiles are available
    public boolean isIAvailable(){
        return iCount > 0;
    }

    // Decrements the count of 'i' shaped tiles
    public void useI(){
        iCount -= 1;
    }

    // Checks if 'L' shaped tiles are available
    public boolean isLAvailable(){
        return lCount > 0;
    }

    // Decrements the count of 'L' shaped tiles
    public void useL(){
        lCount -= 1;
    }

    // TODO: Check functionality
    // Creates a binary representation of the board to check if the player can move to a certain position
    public int[][] fullBinaryBoard(){
        int[][] binaryBoard = new int[27][27];

        for (int i = 3; i < 24; i++){
            for (int j = 3; j < 24; j++){
                binaryBoard[i][j] = board[i/3][j/3].getIntLayout()[i%3][j%3];
            }
        }

        return binaryBoard;

    }

    public void setBinary(int[][] fullBinaryBoard){
        binary = fullBinaryBoard;
    }

    public int[][] getBinary() {
        return binary;
    }

    public void binaryBoardPrinter(){
        int[][] temp = fullBinaryBoard();
        for (int i = 0; i < 27; i++){
            for (int j = 0; j < 27; j++){
                System.out.print(temp[i][j] + " ");
            }
            System.out.println();
        }
    }

}
