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
                    Image currentTileImage = tileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
                    ImageIcon tileIcon = new ImageIcon(currentTileImage);

                    board.getSet()[i][j].setIcon(tileIcon);

                    // System.out.println(board.getSet()[i][j]);
                    board.getSet()[i][j].setBounds(18 + 60 * (j- 1),22 + 60 * (i - 1),50,50);

                    fixedBoard.add(board.getSet()[i][j]);
                    board.getSet()[i][j].setVisible(true);

                    // new tile creation ends here
                }
            }
        }

//        //////////////////////////////////////////////////
//        //Creating the player #1 icon
//        BufferedImage playerOneImg = null;
//        try {
//            playerOneImg = ImageIO.read(new File("Labyrinth/res/PlayerImages/bluedot.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Image currentPlayerOneImg = playerOneImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
//        ImageIcon playerOneIcon = new ImageIcon(currentPlayerOneImg);
//
//        System.out.println();
//        System.out.println(board.getDeck().getPlayers().get(0));
//        board.getDeck().getPlayers().get(0).setIcon(playerOneIcon);
//        board.getDeck().getPlayers().get(0).setBounds(10, 10, 80, 80);
//        fixedBoard.add(board.getDeck().getPlayers().get(0));
//        board.getDeck().getPlayers().get(0).setVisible(true);
//
//        ////////////////////////////////////////////////////
//
//        //////////////////////////////////////////////////
//        //Creating the player #2 icon
//        BufferedImage playerTwoImg = null;
//        try {
//            playerTwoImg = ImageIO.read(new File("Labyrinth/res/PlayerImages/greendot.png")); // Doesnt exist
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Image currentPlayerTwoImg = playerTwoImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
//        ImageIcon playerTwoIcon = new ImageIcon(currentPlayerTwoImg);
//
//        System.out.println();
//        System.out.println(board.getDeck().getPlayers().get(0));
//        board.getDeck().getPlayers().get(0).setIcon(playerTwoIcon);
//        board.getDeck().getPlayers().get(0).setBounds(10, 10, 80, 80);
//        fixedBoard.add(board.getDeck().getPlayers().get(0));
//        board.getDeck().getPlayers().get(0).setVisible(true);
//
//        ////////////////////////////////////////////////////
//
//        //////////////////////////////////////////////////
//        //Creating the player #3 icon
//        BufferedImage playerThreeImg = null;
//        try {
//            playerThreeImg = ImageIO.read(new File("Labyrinth/res/PlayerImages/orangedot.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Image currentPlayerThreeImg = playerThreeImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
//        ImageIcon playerThreeIcon = new ImageIcon(currentPlayerThreeImg);
//
//        System.out.println();
//        System.out.println(board.getDeck().getPlayers().get(0));
//        board.getDeck().getPlayers().get(0).setIcon(playerThreeIcon);
//        board.getDeck().getPlayers().get(0).setBounds(10, 10, 80, 80);
//        fixedBoard.add(board.getDeck().getPlayers().get(0));
//        board.getDeck().getPlayers().get(0).setVisible(true);
//
//        ////////////////////////////////////////////////////
//
//        //////////////////////////////////////////////////
//        //Creating the player #4 icon
//        BufferedImage playerFourImg = null;
//        try {
//            playerFourImg = ImageIO.read(new File("Labyrinth/res/bluedot.png")); // Doesnt exist
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Image currentPlayerFourImg = playerFourImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
//        ImageIcon playerFourIcon = new ImageIcon(currentPlayerFourImg);
//
//        System.out.println();
//        System.out.println(board.getDeck().getPlayers().get(0));
//        board.getDeck().getPlayers().get(0).setIcon(playerFourIcon);
//        board.getDeck().getPlayers().get(0).setBounds(10, 10, 80, 80);
//        fixedBoard.add(board.getDeck().getPlayers().get(0));
//        board.getDeck().getPlayers().get(0).setVisible(true);
//
//      ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Dealing the hand out for player ONE
        String[] playerOneHand = board.getDeck().getPlayers().get(0).getPlayerHand();

        for(int x = 0; x < 5; x++){

            BufferedImage picOne = null;
            try {
                picOne = ImageIO.read(new File("Labyrinth/res/cards/" + playerOneHand[x] + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image currentHandOne = picOne.getScaledInstance(80,150, Image.SCALE_SMOOTH);
            ImageIcon cardIconOne = new ImageIcon(currentHandOne);

            JLabel cardOne = new JLabel(cardIconOne);
            cardOne.setBounds(10 + (x * 70), 100, 80, 150);
            add(cardOne);
            setVisible(true);
        }
        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Dealing the hand out for player TWO
        String[] playerTwoHand = board.getDeck().getPlayers().get(1).getPlayerHand();

        for(int y = 0; y < 5; y++){

            BufferedImage picTwo = null;
            try {
                picTwo = ImageIO.read(new File("Labyrinth/res/cards/" + playerTwoHand[y] + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image currentHandTwo = picTwo.getScaledInstance(80,150, Image.SCALE_SMOOTH);
            ImageIcon cardIconTwo = new ImageIcon(currentHandTwo);

            JLabel cardTwo = new JLabel(cardIconTwo);
            cardTwo.setBounds(10 + (y * 70), 400, 80, 150);
            add(cardTwo);
            setVisible(true);
        }
        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Dealing the hand out for player THREE
        String[] playerThreeHand = board.getDeck().getPlayers().get(2).getPlayerHand();

        for(int i = 0; i < 5; i++){

            BufferedImage picThree = null;
            try {
                picThree = ImageIO.read(new File("Labyrinth/res/cards/" + playerThreeHand[i] + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image currentHandThree = picThree.getScaledInstance(80,150, Image.SCALE_SMOOTH);
            ImageIcon cardIconThree = new ImageIcon(currentHandThree);

            JLabel cardThree = new JLabel(cardIconThree);
            cardThree.setBounds(1000 + (i * 70), 100, 80, 150);
            add(cardThree);
            setVisible(true);
        }
        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Dealing the hand out for player ONE
        String[] playerFourHand = board.getDeck().getPlayers().get(3).getPlayerHand();

        for(int z = 0; z < 5; z++){

            BufferedImage picFour = null;
            try {
                picFour = ImageIO.read(new File("Labyrinth/res/cards/" + playerFourHand[z] + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image currentHandFour = picFour.getScaledInstance(80,150, Image.SCALE_SMOOTH);
            ImageIcon cardIconFour = new ImageIcon(currentHandFour);

            JLabel card = new JLabel(cardIconFour);
            card.setBounds(1000 + (z * 70), 400, 80, 150);
            add(card);
            setVisible(true);
        }
        ////////////////////////////////////////////////////

        //Closes program if the exit option is clicked.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        repaint();

    }

    //Creates the menu bar where instruction, save and load functionalities can be accessed
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
