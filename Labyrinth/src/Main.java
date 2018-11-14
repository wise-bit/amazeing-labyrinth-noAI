/*
Author: Satrajit, Shrill
 */

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, FontFormatException {
        // new HomePageGUI();

        // Creates a new setup
        // Setup s = new Setup();
//
//        // Prints all tiles
//        Tile[][] set = s.getBoard();
//        for (int i = 0; i < 9; i++){
//            for (int j = 0; j < 9; j++){
//                if (set[i][j] != null && set[i][j].isMoveable()) {
//                    System.out.println(set[i][j].makeFileName());
//                }
//            }
//        }

        // Generates binary maze
//        for (int i = 0; i < 27; i++){
//            for (int j = 0; j < 27; j++){
//                System.out.print(s.fullBinaryBoard()[i][j] + " ");
//            }
//            System.out.println();
//        }

        // Shows list of all of the treasures
//        ArrayList<String> list = s.listOfTreasures();
//        for (int i = 0; i < 24; i++)
//            System.out.print(list.get(i) + ", ");

        // new HomePageGUI();
        new GameGUI();
        // new UX().save(s.getBoard());

    }
}
