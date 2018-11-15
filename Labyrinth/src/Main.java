/*
Author: Satrajit, Shrill
 */

import java.awt.*;
import java.io.IOException;

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


        // new HomePageGUI();
        new Setup();
        new GameGUI();

        // new UX().save(s.getBoard());
        // new Instructions();

    }
}
