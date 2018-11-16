/*
Author: Satrajit, Shrill
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static Tile[][] board;
    public static int[][] binary;
    public static Deck deck;
    public static Tile extraTile = new Tile("Empty", true, 'l', 0, 0, 0);;
    public static String[] visibleNames = new String[4];
    public static Player[] playerNames = new Player[4];
    public static JTextField[] names = new JTextField[4];

    public static void main(String[] args) throws IOException, FontFormatException {

        // new HomePageGUI();
        new Setup();
        new Deck(4);
        new HomePageGUI();

        // new UX().save(s.getBoard());
        // new Instructions();

    }
}
