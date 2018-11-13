/**
 * Author: Shrill
 * Author: Lazar
 * Author: Satrajit
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class GameGUI extends JFrame implements ActionListener {

    Board board = new Board();
    private JLabel fixedBoard;

    public GameGUI() throws IOException {

        setLayout(null);
        setBounds(0, 0, board.getDim().width, board.getDim().height);
        this.setTitle("aMAZEing Labyrinth");

        // setContentPane(fixedBoard);

        // Creates a scaleable image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Labyrinth/res/blogamazeingboard.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(board.getDim().height/2, board.getDim().height/2, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        fixedBoard = new JLabel(imageIcon);
        fixedBoard.setBounds(board.getDim().width/4,board.getDim().height/4,board.getDim().width/2,board.getDim().height/2);
        fixedBoard.setVisible(true);
        add(fixedBoard);
        // Main fixed board image ends here

        // Places the other tiles
        for (int i = 2; i < 9; i+=2){
            for (int j = 2; j < 9; j+=2){
                // Only if the tile is not a null
                if (board.getSet()[i][j] != null){
                    // new tile creation begins here

                    String fileName = board.getSet()[i][j].makeFileName();

                    BufferedImage tileImg = null;
                    try {
                        tileImg = ImageIO.read(new File("Labyrinth/res/Images/" + fileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image currentTileImage = tileImg.getScaledInstance(1,1, Image.SCALE_SMOOTH);// TODO: Change
                    ImageIcon tileIcon = new ImageIcon(currentTileImage);

                    board.getSet()[i][j].setIcon(tileIcon);
                    board.getSet()[i][j].setBounds(0,0,0,0); // TODO: Chnage
                    add(board.getSet()[i][j]);
                    board.getSet()[i][j].setVisible(true);

                    // new tile creation ends here
                }
            }
        }

        //Closes program if the exit option is clicked.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
