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

    private ExtraMethods extra = new ExtraMethods();

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
                ", extra=" + extra +
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

        if (row == getRows() && column == getColumns()) {
            return false;
        } else if (row == getRows() && column == getColumns() && move > 1) {
            return true;
        } else {
            for (int count = 0; count < 4; count++) {
                switch (count) {
                    case LEFT:
                        if (validMove(row, column-3, count)) {
                            if (movePlayer(row, column-3, move+3)) {
                                return true;
                            }
                        }
                        break;
                    case RIGHT:
                        if (validMove(row, column+3, count)) {
                            if (movePlayer(row, column+3, move+3)) {
                                return true;
                            }
                        }
                        break;
                    case UP:
                        if (validMove(row - 3, column, count)) {
                            if (movePlayer(row - 3, column, move+3)) {
                                return true;
                            }
                        }
                        break;
                    case DOWN:
                        if (validMove(row + 3, column, count)) {
                            if (movePlayer(row + 3, column, move+3)) {
                                return true;
                            }
                        }
                        break;
                }
            }
        }
        return false;

    }

    public boolean validMove(int row, int column, int count) {
        try {
            if (count == UP)
                return extra.s.fullBinaryBoard()[row-1][column] == 1 && extra.s.fullBinaryBoard()[row-2][column] == 1 && extra.s.fullBinaryBoard()[row-3][column] == 1 && row > 3;
            else if (count == DOWN)
                return extra.s.fullBinaryBoard()[row+1][column] == 1 && extra.s.fullBinaryBoard()[row+2][column] == 1 && extra.s.fullBinaryBoard()[row+3][column] == 1 && row < 24;
            else if (count == LEFT)
                return extra.s.fullBinaryBoard()[row][column-1] == 1 && extra.s.fullBinaryBoard()[row][column-2] == 1 && extra.s.fullBinaryBoard()[row][column-3] == 1 && column > 3;
            else if (count == RIGHT)
                return extra.s.fullBinaryBoard()[row][column+1] == 1 && extra.s.fullBinaryBoard()[row][column+2] == 1 && extra.s.fullBinaryBoard()[row][column+3] == 1 && column < 24;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

}
