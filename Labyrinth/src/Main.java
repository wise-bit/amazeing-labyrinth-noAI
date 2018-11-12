/*
Author: Satrajit, Shrill
 */

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, FontFormatException {
        new HomePageGUI();
        Player[] players = new Player[4];
        //new HomePageGUI();

        Setup s = new Setup();

//        Tile[][] set = s.getBoard();
//        for (int i = 1; i < 8; i++){
//            for (int j = 1; j < 8; j++){
//                System.out.println(set[i][j].makeFileName());
//            }
//        }

        // Generates binary maze
        for (int i = 0; i < 27; i++){
            for (int j = 0; j < 27; j++){
                System.out.print(s.fullBinaryBoard()[i][j] + " ");
            }
            System.out.println();
        }

        // New stuff starts here:

        ArrayList<String> list = s.listOfTreasures();
        for (int i = 0; i < 24; i++)
            System.out.print(list.get(i) + ", ");

        // To get list of treasures Call:
        // s.listOfTreasures()
        // This is already randomized / scrambled
        // So just assign to different players using a for loop
        // Sorry if I did too much in this method, it just seemed simple to do it all at the same place

    }
  
}
