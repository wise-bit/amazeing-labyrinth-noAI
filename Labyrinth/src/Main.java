/*
Author: Satrajit's version
 */

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  
    public static void main(String[] args) throws IOException, FontFormatException {

        Player[] players = new Player[4];
        Setup s = new Setup();

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                System.out.println(s.getBoard()[i][j]);
            }
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
      
        // new HomePageGUI();

    }
  
}
