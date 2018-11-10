/*
Author: Satrajit Chatterjee
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Setup extends JFrame {

    private Random rand = new Random();

    private Tile[][] board;
    private File text = new File("C:\\Users\\satra\\IdeaProjects\\SLS\\Labyrinth\\res\\treasures.txt");
    Scanner file = new Scanner(text);

    private int lCount = 9;
    private int iCount = 13;

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

            boolean changed = false;

            while (changed == false) {

                int row = rand.nextInt(4) * 2 + 1;
                int column = rand.nextInt(4) * 2 + 1;

                if (getBoard()[row][column] == null) {

                    changed = true;
                    this.board[row][column] =
                            new Tile(attribs[1], Boolean.parseBoolean(attribs[1]), attribs[2].charAt(0), row, column, 4);

                }

            }

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
