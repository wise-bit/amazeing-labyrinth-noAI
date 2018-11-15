/*
Author: Satrajit, Shrill
 */

import java.awt.*;
import java.io.IOException;

public class Main {

    public static Tile[][] board;
    public static int[][] binary;
    public static Deck deck;
    public static Tile extraTile;

    public static void main(String[] args) throws IOException, FontFormatException {

        // new HomePageGUI();
        new Setup();
        new Deck(4);
        new GameGUI();

        // new UX().save(s.getBoard());
        // new Instructions();

    }
}
