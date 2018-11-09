/*
Author: Satrajit Chatterjee
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Setup extends JFrame {

    private Tile[][] board;
    private File text = new File("C:\\Users\\satra\\IdeaProjects\\SLS\\Labyrinth\\res\\treasures.txt");
    Scanner file = new Scanner(text);

    public Setup() throws FileNotFoundException {
        init();
    }

    public void init(){

        board = new Tile[9][9];

        while(getFile().hasNextLine()){
            String line = getFile().nextLine();
            fixed(line);
        }
    }

    public void fixed(String attributes){

        String[] attribs = attributes.split(",");

        if (attribs[1].equals("false")){

            int row = Integer.parseInt(attribs[3]);
            int column = Integer.parseInt(attribs[4]);

            System.out.println(this.board[row][column]);
            this.board[row][column] =
                    new Tile(attribs[0], Boolean.parseBoolean(attribs[1]), attribs[2].charAt(0), row, column, Integer.parseInt(attribs[5]));

            //System.out.println(this.board[row][column]);

        }

    }

    public void otherTreasures(String attributes){

        String[] attribs = attributes.split(",");

        if (attribs[1].equals("true")) {



        }

    }

    public void otherTiles(){

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
}
