/*
Author: Shrill Patel
 */

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Player extends JLabel {

    //Varibales to store player attributes
    private String[] playerHand;
    private String playerName;
    private String playerColour;

    //Keeps count of the treasures collected by the player
    private int treasures;
    private ArrayList<String> collected = new ArrayList<String>();

    //Variables to store the players location (coordinates)
    private int rows;
    private int columns;
    
    // for traversal of movePlayer
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    //Constructor Method
    public Player(String[] playerHand, String playerName, String playerColour, int rows, int columns) throws FileNotFoundException {
        this.playerHand = playerHand;
        this.playerName = playerName;
        this.playerColour = playerColour;
        setRows(rows);
        setColumns(columns);
    }

    //Getters and Setters
    public String[] getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(String[] playerHand) {
        this.playerHand = playerHand;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerColour() {
        return playerColour;
    }

    public void setPlayerColour(String playerColour) {
        this.playerColour = playerColour;
    }

    public int getTreasures() {
        return treasures;
    }

    public void setTreasures(int treasures) {
        this.treasures = treasures;
    }

    public ArrayList<String> getCollected() {
        return collected;
    }

    public void setCollected(ArrayList<String> collected) {
        this.collected = collected;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        //Error checking to prevent from going out of bounds
        if(rows > 0)
            this.rows = rows;
        else
            this.rows = 0;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        //Error checking to prevent from going out of bounds
        if(columns > 0)
            this.columns = columns;
        else
            this.columns = 0;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerHand=" + Arrays.toString(playerHand) +
                ", playerName='" + playerName + '\'' +
                ", playerColour='" + playerColour + '\'' +
                ", treasures=" + treasures +
                ", collected=" + collected +
                ", rows=" + rows +
                ", columns=" + columns +
                '}';
    }

    //Helper Methods

    //Method to help increment the treasure count when player collects them
    public void incrementTreasure(){
        this.treasures++;
    }

    public void removeCard(){
        int count = 5;
        for (int x = 0; x < playerHand.length; x++) {
            if (collected.contains(playerHand[x])) {
                collected.remove(playerHand[x]);
                count -= 1;
            }
        }
    }

    public boolean movePlayer(int row, int column, int move){

        System.out.println(row + " " + column + " " + getRows() + " " + getColumns());

//        int temp = row;
//        row = rows;
//        rows = temp;
//
//        temp = column;
//        column = columns;
//        columns = temp;

        if (row == getRows() && column == getColumns()*3+1) {
            return false;
        } else if (row == getRows() && column == getColumns() && move > 1) {
            return true;
        } else {
            for (int count = 0; count < 4; count++) {
                switch (count) {
                    case LEFT:
                        columns -= 1;
                        if (validMove(row, column, count)) {
                            if (movePlayer(row, column, move+1)) {
                                return true;
                            }
                        }
                        columns += 1;
                        break;
                    case RIGHT:
                        columns += 1;
                        if (validMove(row, column, count)) {
                            if (movePlayer(row, column, move+1)) {
                                return true;
                            }
                        }
                        columns -= 1;
                        break;
                    case UP:
                        rows -= 1;
                        if (validMove(row, column, count)) {
                            if (movePlayer(row, column, move+1)) {
                                return true;
                            }
                        }
                        rows += 1;
                        break;
                    case DOWN:
                        rows += 1;
                        if (validMove(row, column, count)) {
                            if (movePlayer(row, column, move+1)) {
                                return true;
                            }
                        }
                        rows -= 1;
                        break;
                }
            }
        }
        return false;

    }

    public boolean validMove(int row, int column, int count) {
        // TODO: Error in binary board
        try {
            if (count == UP)
                return Main.binary[rows*3+1 - 1][columns*3+1] == 1 && Main.binary[rows*3+1 - 2][columns*3+1] == 1 && Main.binary[rows*3+1 - 3][columns*3+1] == 1 && rows*3+1 > 3;
            else if (count == DOWN)
                return Main.binary[rows*3+1 + 1][columns*3+1] == 1 && Main.binary[rows*3+1 + 2][columns*3+1] == 1 && Main.binary[rows*3+1 + 3][columns*3+1] == 1 && rows*3+1 < 24;
            else if (count == LEFT)
                return Main.binary[rows*3+1][columns*3+1 - 1] == 1 && Main.binary[rows*3+1][columns*3+1 - 2] == 1 && Main.binary[rows*3+1][columns*3+1 - 3] == 1 && columns*3+1 > 3;
            else if (count == RIGHT) {
                System.out.println(Main.binary[rows*3+1][columns*3+1 + 1]);
                System.out.println(Main.binary[rows*3+1][columns*3+1 + 2]);
                System.out.println(Main.binary[rows*3+1][columns*3+1 + 3]);
                return Main.binary[rows*3+1][columns*3+1 + 1] == 1 && Main.binary[rows*3+1][columns*3+1 + 2] == 1 && Main.binary[rows*3-1][columns*3+1 + 3] == 1 && columns*3+1 < 24;
            }
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

}
