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
    private JMenuBar menuBar = new JMenuBar();
    private JMenu help = new JMenu("Help");
    private JMenuItem instructions = new JMenuItem("Instructions");
    private JMenu file = new JMenu("File");
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem load = new JMenuItem("Load");
    private Player[] player = new Player[4];

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
        fixedBoard.setBounds(board.getDim().width/3,board.getDim().height/10,board.getDim().height/2,board.getDim().height/2);
        fixedBoard.setVisible(true);
        add(fixedBoard);
        // Main fixed board image ends here

        //Creates the menu bar
        setJMenuBar(menuBar);
        menuBar.add(help);
        menuBar.add(file);
        help.add(instructions);
        file.add(save);
        file.add(load);

        // Places the other tiles
        for (int i = 0; i < 9; i++){

            for (int j = 0; j < 9; j++){

                // Only if the tile is not a null
                if (board.getSet()[i][j] != null && board.getSet()[i][j].isMoveable()){
                    // new tile creation begins here

                    String fileName = board.getSet()[i][j].makeFileName();

                    BufferedImage tileImg = null;
                    try {
                        tileImg = ImageIO.read(new File("Labyrinth/res/Images/" + fileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image currentTileImage = tileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);// TODO: Change
                    ImageIcon tileIcon = new ImageIcon(currentTileImage);

                    board.getSet()[i][j].setIcon(tileIcon);

                    System.out.println(board.getSet()[i][j]);
                    board.getSet()[i][j].setBounds(18 + 60 * (j- 1),22 + 60 * (i - 1),50,50); // TODO: Chnage

                    fixedBoard.add(board.getSet()[i][j]);
                    board.getSet()[i][j].setVisible(true);

                    // new tile creation ends here
                }
            }
        }

        //Creating the player #1 icon
        BufferedImage playerOneImg = null;
        try {
            playerOneImg = ImageIO.read(new File("Labyrinth/res/PlayerImages/bluedot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image currentPlayerOneImg = playerOneImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);// TODO: Change
        ImageIcon playerOneIcon = new ImageIcon(currentPlayerOneImg);
        setBounds(10, 10, 80, 80);
        fixedBoard.add(playerOneIcon);
        setVisible(true);

//        //Handing out the cards for each player
//        for(int x = 0; x < 4; x++){
//            board.getDeck().getPlayers().get(x).getPlayerName();
//            System.out.println();
//        }

        //

        //Closes program if the exit option is clicked.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        repaint();

    }

    public void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu help = new JMenu("Help");
        menuBar.add(help);

        JMenuItem instructions = new JMenuItem("Instructions");
        help.add(instructions);

        JMenu file = new JMenu("File");
        menuBar.add(file);

        JMenuItem save = new JMenuItem("Save");
        file.add(save);

        JMenuItem load = new JMenuItem("Load");
        file.add(load);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == instructions) {
            try {
                new HomePageGUI();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (FontFormatException e1) {
                e1.printStackTrace();
            }
        }
    }
}
