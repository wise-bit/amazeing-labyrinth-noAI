/*
Author: Satrajit's version
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  
    public static void main(String[] args) throws IOException {

        Player[] players = new Player[4];
        new HomePageGUI();
        Setup s = new Setup();

//        for (int i = 0; i < 9; i++){
//            for (int j = 0; j < 9; j++){
//                System.out.println(s.getBoard()[i][j]);
//            }
//        }

        // New stuff starts here:

        ArrayList<String> list = s.listOfTreasures();
        for (int i = 0; i < 24; i++)
            System.out.print(list.get(i) + ", ");

    }
  
}
