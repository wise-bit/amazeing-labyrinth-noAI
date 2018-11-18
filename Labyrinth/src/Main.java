/*
Author: Satrajit, Shrill
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static Tile[][] board;
    public static int[][] binary;
    public static Deck deck;
    public static Tile extraTile;
    public static String[] visibleNames = new String[4];
    public static Player[] playerNames = new Player[4];
    public static JTextField[] names = new JTextField[4];

    public static void main(String[] args) throws IOException, FontFormatException {

        // new HomePageGUI();
        new Setup();
        extraTile.setVisible(true);
        new Deck(4);

        BufferedImage extraTileImg = null;
        extraTile.setIcon(null);
        try{
            extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.extraTile.makeFileName()));
            System.out.println("Labyrinth/res/Images/" + Main.extraTile.makeFileName());
        } catch (IOException e1){
            e1.printStackTrace();
        }
        Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        ImageIcon tileIcon = new ImageIcon(currentTileImage);

        extraTile.setIcon(tileIcon);

        new GameGUI();

        // new UX().save(s.getBoard());
        // new Instructions();

    }

}
