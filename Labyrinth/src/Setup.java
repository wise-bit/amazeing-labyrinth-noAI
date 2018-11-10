/*
Author: Satrajit Chatterjee
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Setup extends JFrame {

    private Random rand = new Random();

    private Tile[][] board;


    /// Variable initializations


    private ArrayList<Integer> counter = new ArrayList<Integer>();

    private File text = new File("C:\\Users\\satra\\IdeaProjects\\SLS\\Labyrinth\\res\\treasures.txt");

    Scanner file = new Scanner(text);

    private int lCount = 9;
    private int iCount = 13;


    /// Methods


    public Setup() throws FileNotFoundException {
        init();
    }

    public ArrayList<Integer> getCounter() {
        return counter;
    }

    public void setCounter(ArrayList<Integer> counter) {
        this.counter = counter;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public File getText() {
        return text;
    }

    public void setText(File text) {
        this.text = text;
    }

    public Scanner getFile() {
        return file;
    }

    public void setFile(Scanner file) {
        this.file = file;
    }


    /// HELPER METHODS (Not all commented)


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

    }

    public void fixed(String attributes){

        String[] attribs = attributes.split(",");

        if (attribs[1].equals("false")) {

            int row = Integer.parseInt(attribs[3]);
            int column = Integer.parseInt(attribs[4]);

            this.board[row][column] =
                    new Tile(attribs[0], Boolean.parseBoolean(attribs[1]), attribs[2].charAt(0), row, column, Integer.parseInt(attribs[5]));

            this.counter.remove(new Integer(row * 7 + column));

            // System.out.println(this.board[row][column]);

        }

    }

    // Place the other treasures
    public void otherTreasures(String attributes){

        // Splits it into the attributes as string array
        String[] attribs = attributes.split(",");

        // Only if the tile is moveable
        if (attribs[1].equals("true")) {

            int row = counter.get(1)/7;
            int column = counter.get(1)%7;

            if (getBoard()[row][column] == null) {

                // System.out.println("AAA");

                this.board[row][column] =
                        new Tile(attribs[0], Boolean.parseBoolean(attribs[1]), attribs[2].charAt(0), row, column, 4);

            }

            counter.remove(1);

        }

    }

    public void otherTiles(){

        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {

                if (i == 1 && j == 1) this.board[i][j] = new Tile("Start1", false, 'l', i, j, 0);
                else if (i == 1 && j == 7) this.board[i][j] = new Tile("Start2", false, 'l', i, j, 1);
                else if (i == 7 && j == 7) this.board[i][j] = new Tile("Start3", false, 'l', i, j, 2);
                else if (i == 7 && j == 1) this.board[i][j] = new Tile("Start4", false, 'l', i, j, 3);

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

                }
            }
        }

    }

    public void initializeCounter(){

        for (int i = 7; i < 56; i++){
            this.counter.add(i);
        }

        Collections.shuffle(this.counter);

    }

    public boolean isIAvailable(){
        return iCount > 0;
    }

    public void useI(){
        iCount -= 1;
    }

    public boolean isLAvailable(){
        return lCount > 0;
    }

    public void useL(){
        lCount -= 1;
    }


}
