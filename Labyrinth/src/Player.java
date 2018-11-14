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
//            System.out.printf("Total moves: %d - press 'ENTER' to continue...\n", move);
//            System.in.read();
            for (int count = 0; count < 4; count++) {
                switch (count) {
                    case LEFT:
                        if (validMove(row, column-1)) {
                            if (movePlayer(row, column-1, move+1)) {
                                return true;
                            }
                        }
                        break;
                    case RIGHT:
                        if (validMove(row, column+1)) {
                            if (movePlayer(row, column+1, move+1)) {
                                return true;
                            }
                        }
                        break;
                    case UP:
                        if (validMove(row - 1, column)) {
                            if (movePlayer(row - 1, column, move+1)) {
                                return true;
                            }
                        }
                        break;
                    case DOWN:
                        if (validMove(row + 1, column)) {
                            if (movePlayer(row + 1, column, move+1)) {
                                return true;
                            }
                        }
                        break;
                }
            }
        }
        return false;

    }

    public static boolean validMove(int row, int column) {
        try {
            return MAZE[row][column] == s;
        } catch (Exception e) {
            return false;
        }
    }

}
