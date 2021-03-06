/**
 * Author: Shrill Patel
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

    public String toStorageString() {
        String hand = String.join(":", playerHand);
        return hand + "," + playerName + "," + playerColour + "," + treasures + "," + rows + "," + columns;
    }

    //Helper Methods

    //Method to help increment the treasure count when player collects them
    public void incrementTreasure(){
        this.treasures++;
    }

    public void removeCard(String currentTile){

        for (int x = 0; x < playerHand.length; x++) {

            if (playerHand[x].equals(currentTile)) {

                playerHand[x] = "";
                incrementTreasure();

            }
        }
    }

    public boolean movePlayer(int[][] modifyableBoard, int end_row, int end_column, int row, int column, int move){

        System.out.println(row + " " + column + " " + end_row + " " + end_column);
        modifyableBoard[row*3+1][column*3+1] = 0;

        if (row == end_row && column == end_column) {
            return true;
        } else if (row == getRows() && column == getColumns() && move > 1) {
            return false;
        } else {
            for (int count = 0; count < 4; count++) {
                switch (count) {
                    case LEFT:
                        if (validMove(modifyableBoard, row, column, count)) {
                            if (movePlayer(modifyableBoard, end_row, end_column, row, column-1, move+1)) {
                                return true;
                            }
                        }
                        break;
                    case RIGHT:
                        if (validMove(modifyableBoard, row, column, count)) {
                            if (movePlayer(modifyableBoard, end_row, end_column, row, column+1, move+1)) {
                                return true;
                            }
                        }
                        break;
                    case UP:
                        if (validMove(modifyableBoard, row, column, count)) {
                            if (movePlayer(modifyableBoard, end_row, end_column, row-1, column, move+1)) {
                                return true;
                            }
                        }
                        break;
                    case DOWN:
                        if (validMove(modifyableBoard, row, column, count)) {
                            if (movePlayer(modifyableBoard, end_row, end_column, row+1, column, move+1)) {
                                return true;
                            }
                        }
                        break;
                    default:
                        return false;
                }
            }
            modifyableBoard[row*3+1][column*3+1] = 0;
        }
        return false;

    }

    public boolean validMove(int[][] modifyableBoard, int row, int column, int count) {
        try {
            if (count == UP) {
                return modifyableBoard[row * 3 + 1 - 1][column * 3 + 1] == 1 && modifyableBoard[row * 3 + 1 - 2][column * 3 + 1] == 1 && modifyableBoard[row * 3 + 1 - 3][column * 3 + 1] == 1 && row * 3 + 1 > 3;

            }
            else if (count == DOWN) {
                return modifyableBoard[row * 3 + 1 + 1][column * 3 + 1] == 1 && modifyableBoard[row * 3 + 1 + 2][column * 3 + 1] == 1 && modifyableBoard[row * 3 + 1 + 3][column * 3 + 1] == 1 && row * 3 + 1 < 24;

            }
            else if (count == LEFT) {
                return modifyableBoard[row * 3 + 1][column * 3 + 1 - 1] == 1 && modifyableBoard[row * 3 + 1][column * 3 + 1 - 2] == 1 && modifyableBoard[row * 3 + 1][column * 3 + 1 - 3] == 1 && column * 3 + 1 > 3;

            }
            else if (count == RIGHT) {
                return modifyableBoard[row*3+1][column*3+1 + 1] == 1 && modifyableBoard[row*3+1][column*3+1 + 2] == 1 && modifyableBoard[row*3+1][column*3+1 + 3] == 1 && column < 8;
            }
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

}
