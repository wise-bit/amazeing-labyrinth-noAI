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
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                // Only if the tile is not a null
                if (board.getSet()[i][j] != null){
                    // new tile creation begins here

                    board.getSet()[i][j].makeFileName();

                    //TODO: Make each of these tiles

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
