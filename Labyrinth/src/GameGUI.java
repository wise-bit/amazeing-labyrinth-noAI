
/**
 * Author: Shrill
 * Author: Lazar
 * Author: Satrajit
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class GameGUI extends JFrame implements ActionListener, MouseListener {

    private JLabel fixedBoard;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu help = new JMenu("Help");
    private JMenuItem instructions = new JMenuItem("Instructions");
    private JMenu file = new JMenu("File");
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem load = new JMenuItem("Load");
    private ImageIcon[] dots = new ImageIcon[4];
    private JLabel[] playerColour = new JLabel[4];
    private JLabel[] playerName = new JLabel[4];
    private int currentPlayer = 0;
    private Font font = new Font("Sylfaen", Font.BOLD, 40); // Freestyle Script, Matura MT Script Capitals, French Script MT

    private JButton[] topButtons = new JButton[3];
    private JButton[] bottomButtons = new JButton[3];
    private JButton[] leftButtons = new JButton[3];
    private JButton[] rightButtons = new JButton[3];


    private JLabel extraTile = new JLabel();

    public GameGUI() throws IOException {

        setLayout(null);
        setBounds(0, 0, Board.dim.width, Board.dim.height);
        this.setTitle("aMAZEing Labyrinth");

        // setContentPane(fixedBoard);

        // Creates a scaleable image
        BufferedImage img = null;
        try {
            // img = ImageIO.read(new File("Labyrinth/res/white.jpg"));
            img = ImageIO.read(new File("Labyrinth/res/blogamazeingBoard.jpg")); // TODO: Switch Back
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(Board.dim.height/2, Board.dim.height/2, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        fixedBoard = new JLabel(imageIcon);
        fixedBoard.setBounds(Board.dim.width/3,Board.dim.height/10,Board.dim.height/2,Board.dim.height/2);
        fixedBoard.setVisible(true);
        add(fixedBoard);
        // Main fixed Board image ends here

        //Creates the menu bar
        setJMenuBar(menuBar);
        menuBar.add(help);
        menuBar.add(file);
        help.add(instructions);
        file.add(save);
        file.add(load);
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                new Instructions();
            }
        });

        // Places the other tiles
        for (int i = 0; i < 9; i++){

            for (int j = 0; j < 9; j++){

                // Only if the tile is not a null
                if (Main.board[i][j] != null && Main.board[i][j].isMoveable()){
                    // new tile creation begins here

                    String fileName = Main.board[i][j].makeFileName();

                    BufferedImage tileImg = null;
                    try {
                        tileImg = ImageIO.read(new File("Labyrinth/res/Images/" + fileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image currentTileImage = tileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
                    ImageIcon tileIcon = new ImageIcon(currentTileImage);

                    Main.board[i][j].setIcon(tileIcon);

                    // System.out.println(Main.board[i][j]);
                    Main.board[i][j].setBounds(18 + 60 * (j- 1),22 + 60 * (i - 1),50,50);
                    Main.board[i][j].addMouseListener(this);

                    fixedBoard.add(Main.board[i][j]);
                    Main.board[i][j].setVisible(true);

                    // new tile creation ends here
                } else if (Main.board[i][j] != null ) {

                    Main.board[i][j].setBounds(18 + 60 * (j- 1),22 + 60 * (i - 1),50,50);
                    Main.board[i][j].addMouseListener(this);

                    fixedBoard.add(Main.board[i][j]);
                    Main.board[i][j].setVisible(true);

                }

            }
        }

        //////////////////////////////////////////////////
        //Make player #1 name show up
        playerName[0] = new JLabel(Main.visibleNames[0]);
        playerName[0].setForeground(Color.BLACK);
        playerName[0].setFont(font);
        playerName[0].setBounds(100, 10, 200, 80);
        add(playerName[0]);
        playerName[0].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #2 name show up
        playerName[1] = new JLabel(Main.visibleNames[1]);
        playerName[1].setForeground(Color.BLACK);
        playerName[1].setFont(font);
        playerName[1].setBounds(100, 310, 200, 80);
        add(playerName[1]);
        playerName[1].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #3 name show up
        playerName[2] = new JLabel(Main.visibleNames[2]);
        playerName[2].setForeground(Color.BLACK);
        playerName[2].setFont(font);
        playerName[2].setBounds(1100, 10, 200, 80);
        add(playerName[2]);
        playerName[2].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #4 name show up
        playerName[3] = new JLabel(Main.visibleNames[3]);
        playerName[3].setForeground(Color.BLACK);
        playerName[3].setFont(font);
        playerName[3].setBounds(1100, 310, 200, 80);
        add(playerName[3]);
        playerName[3].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Creating the player #1 icon
        BufferedImage playerOneImg = null;
        try {
            playerOneImg = ImageIO.read(new File("Labyrinth/res/PlayerImagesOnBoard/reddot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image currentPlayerOneImg = playerOneImg.getScaledInstance(20,20, Image.SCALE_SMOOTH);
        ImageIcon playerOneIcon = new ImageIcon(currentPlayerOneImg);

        System.out.println();
        System.out.println(Main.deck.players.get(0));
        Main.deck.players.get(0).setIcon(playerOneIcon);
        Main.deck.players.get(0).setBounds(35, 10, 80, 80);
        fixedBoard.add(Main.deck.players.get(0));
        Main.deck.players.get(0).setVisible(true);

        ////////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Creating the player #2 icon
        BufferedImage playerTwoImg = null;
        try {
            playerTwoImg = ImageIO.read(new File("Labyrinth/res/PlayerImagesOnBoard/yellowdot.png")); // Doesnt exist
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image currentPlayerTwoImg = playerTwoImg.getScaledInstance(20,20, Image.SCALE_SMOOTH);
        ImageIcon playerTwoIcon = new ImageIcon(currentPlayerTwoImg);

        System.out.println();
        System.out.println(Main.deck.players.get(1));
        Main.deck.players.get(1).setIcon(playerTwoIcon);
        Main.deck.players.get(1).setBounds(390, 37, 20, 20);
        fixedBoard.add(Main.deck.players.get(1));
        Main.deck.players.get(1).setVisible(true);

        ////////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Creating the player #3 icon
        BufferedImage playerThreeImg = null;
        try {
            playerThreeImg = ImageIO.read(new File("Labyrinth/res/PlayerImagesOnBoard/bluedot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image currentPlayerThreeImg = playerThreeImg.getScaledInstance(20,20, Image.SCALE_SMOOTH);
        ImageIcon playerThreeIcon = new ImageIcon(currentPlayerThreeImg);

        System.out.println();
        System.out.println(Main.deck.players.get(2));
        Main.deck.players.get(2).setIcon(playerThreeIcon);
        Main.deck.players.get(2).setBounds(393, 393, 20, 20);
        fixedBoard.add(Main.deck.players.get(2));
        Main.deck.players.get(2).setVisible(true);

        ////////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Creating the player #4 icon
        BufferedImage playerFourImg = null;
        try {
            playerFourImg = ImageIO.read(new File("Labyrinth/res/PlayerImagesOnBoard/greendot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image currentPlayerFourImg = playerFourImg.getScaledInstance(20,20, Image.SCALE_SMOOTH);
        ImageIcon playerFourIcon = new ImageIcon(currentPlayerFourImg);

        System.out.println();

        System.out.println(Main.deck.players.get(3));
        Main.deck.players.get(3).setIcon(playerFourIcon);
        Main.deck.players.get(3).setBounds(38, 366, 80, 80);
        fixedBoard.add(Main.deck.players.get(3));
        Main.deck.players.get(3).setVisible(true);

        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Dealing the hand out for player ONE
        String[] playerOneHand = Main.deck.players.get(0).getPlayerHand();

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
        String[] playerTwoHand = Main.deck.players.get(1).getPlayerHand();

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
        String[] playerThreeHand = Main.deck.players.get(2).getPlayerHand();

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

        // System.out.println(Main.deck.players.get(0));
        // Main.deck.players.get(0).setIcon(playerFourIcon);
        // Main.deck.players.get(0).setBounds(10, fixedBoard.getY()-10, 50, 50);
        // fixedBoard.add(Main.deck.players.get(0));
        // Main.deck.players.get(0).setVisible(true);

        ////////////////////////////////////////////////////
        //Dealing the hand out for player FOUR
        String[] playerFourHand = Main.deck.players.get(3).getPlayerHand();

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

        ////////////////////////////////////////////////////
        //Buttons to shift the top columns
        for(int t = 0; t < 3; t++) {
            topButtons[t] = new JButton();
            topButtons[t].setBounds(561 + (t * 117), 40, 50, 50);
            topButtons[t].addActionListener(this);
            add(topButtons[t]);
        }
        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Buttons to shift the bottom columns
        for(int b = 0; b < 3; b++) {
            bottomButtons[b] = new JButton();
            bottomButtons[b].setBounds(565 + (b * 117), 540, 50, 50);
            bottomButtons[b].addActionListener(this);
            add(bottomButtons[b]);
        }
        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Buttons to shift the left side rows
        for(int l = 0; l < 3; l++) {
            leftButtons[l] = new JButton();
            leftButtons[l].setBounds(430, 175 + (l * 117), 50, 50);
            leftButtons[l].addActionListener(this);
            add(leftButtons[l]);
        }
        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Buttons to shift the right side rows
        for(int r = 0; r < 3; r++){
            rightButtons[r] = new JButton();
            rightButtons[r].setBounds(931, 175 + (r * 117), 50, 50);
            rightButtons[r].addActionListener(this);
            add(rightButtons[r]);
        }
        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Adds the extra tile onto the Board and allows for rotation

        BufferedImage extraTileImg = null;
        try {
            extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.extraTile.makeFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        ImageIcon tileIcon = new ImageIcon(currentTileImage);

        extraTile.setIcon(tileIcon);

        extraTile.setBounds(100, 600, 100, 100);
        extraTile.addMouseListener(this);
        add(extraTile);
        setVisible(true);

        System.out.println(Main.deck.players.get(0).getPlayerName());

        //Closes program if the exit option is clicked.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addDots();
        setVisible(true);
        // setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize(1440, 900);
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

    //Adds the dots that the player is associated with in the game
    public void addDots(){
        dots[0] = new ImageIcon("Labyrinth/res/PlayerImages/reddot.png");
        playerColour[0] = new JLabel(dots[0]);
        playerColour[0].setBounds(10, 10, 80, 80);
        add(playerColour[0]);

        dots[1] = new ImageIcon("Labyrinth/res/PlayerImages/yellowdot.png");
        playerColour[1] = new JLabel(dots[1]);
        playerColour[1].setBounds(10, 310, 80, 80);
        add(playerColour[1]);

        dots[2] = new ImageIcon("Labyrinth/res/PlayerImages/bluedot.png");
        playerColour[2] = new JLabel(dots[2]);
        playerColour[2].setBounds(1000, 10, 80, 80);
        add(playerColour[2]);

        dots[3] = new ImageIcon("Labyrinth/res/PlayerImages/greendot.png");
        playerColour[3] = new JLabel(dots[3]);
        playerColour[3].setBounds(1000, 310, 80, 80);
        add(playerColour[3]);
    }

    public void addNames(){
        //playerName[0] = new JLabel(Main.deck.players.get(0).getPlayerName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 3; i++) {

            if (e.getSource() == topButtons[i]) {

                Board.shiftBoardTiles(0, 2 + 2 * i, 4);

                if(Main.board[7][2 + 2 * i] == Main.board[8][2 + 2 * i])
                    Main.board[8][2 + 2 * i] = Main.extraTile;
//
//                if(Main.board[1][2 + 2 * i] == null)
//                    Main.board[1][2 + 2 *i] = Main.extraTile;
//
//                binaryBoardPrinter();
//                Setup.fullBinaryBoard();
//                System.out.println();
//
//                Main.board[1][2 + 2 * i].setLocation(18 + 60 * (1 + 2 * i), 25);
//                fixedBoard.add(Main.board[1][2 + 2 * i]);
//                Main.board[1][2 + 2 * i].setVisible(true);
//
//                Main.board[1][2 + 2 * i].repaint();


            }
            else if (e.getSource() == bottomButtons[i]) {

                Board.shiftBoardTiles(8, 2 + 2 * i, 3);

                binaryBoardPrinter();
                Setup.fullBinaryBoard();
                System.out.println();

                Main.board[7][2 + 2 * i].setBounds(18 + 60  * (1 + 2 * i), 375, 50, 50);
                fixedBoard.add(Main.board[7][2 + 2 * i]);

                Main.extraTile.setBounds(78, 300, 50, 50);
                fixedBoard.add(extraTile);
                repaint();

            }
            else if (e.getSource() == rightButtons[i]) {

            }
            else if (e.getSource() == leftButtons[i]) {

            }

        }
        System.out.println(Main.extraTile);
        BufferedImage extraTileImg = null;
        try {
            extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.extraTile.makeFileName()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        ImageIcon tileIcon = new ImageIcon(currentTileImage);

        extraTile.setIcon(tileIcon);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int currentRow = Main.deck.players.get(currentPlayer).getRows();
        int currentColumn = Main.deck.players.get(currentPlayer).getColumns();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == Main.board[i][j]) {
                    System.out.printf("Label (%d, %d) was clicked\n", i, j);
                    // System.out.println();
                    int[][] modifyableBoard = Main.binary;
                    if (Main.deck.players.get(currentPlayer).movePlayer(modifyableBoard, i,j, currentRow, currentColumn, 0) == true){
                        Main.deck.players.get(currentPlayer).setRows(i);
                        Main.deck.players.get(currentPlayer).setColumns(j);
                        System.out.println((35 + 60*Main.deck.players.get(currentPlayer).getColumns()) + " " +  (10 + 60*Main.deck.players.get(currentPlayer).getRows()));
                        Main.deck.players.get(currentPlayer).setBounds(35 + 60*Main.deck.players.get(currentPlayer).getColumns(), 10 + 60*Main.deck.players.get(currentPlayer).getRows(), 80, 80);

// Main.deck.players.get(currentPlayer).validate();
//                        Main.deck.players.get(currentPlayer).repaint();

                        System.out.println("Working");
                        if (currentPlayer == 3)
                            currentPlayer = 0;
                        else
                            currentPlayer++;
                    }
                }
            }
        }
    }

    public int[][] fullBinaryBoard(){
        int[][] binaryBoard = new int[27][27];

        for (int i = 3; i < 24; i++){
            for (int j = 3; j < 24; j++){
                binaryBoard[i][j] = Main.board[i/3][j/3].getIntLayout()[i%3][j%3];
            }
        }

        return binaryBoard;

    }

    public void binaryBoardPrinter(){
        int[][] temp = fullBinaryBoard();
        for (int i = 3; i < 24; i++){
            for (int j = 3; j < 24; j++){
                System.out.print(temp[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    	if (e.getSource() == extraTile) {

    		if (e.getButton() == MouseEvent.BUTTON1) {

                System.out.println(Main.extraTile);
                System.out.println();
    		    /*
    			int[][] array = Main.extraTile.getIntLayout();

    			for (int i = 0; i < 3; i++) {

    				System.out.println(Arrays.toString(array[i]));
    			}
    			*/
    			Main.extraTile.rotateClockwise();
    			System.out.println(Main.extraTile.getRotation());

    			BufferedImage extraTileImg = null;
    			try{
    			    extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.extraTile.makeFileName()));
                } catch (IOException e1){
    			    e1.printStackTrace();
                }
                Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
    			ImageIcon tileIcon = new ImageIcon(currentTileImage);

    			extraTile.setIcon(tileIcon);
    		}
    		else if (e.getButton() == MouseEvent.BUTTON3) {

                Main.extraTile.rotateCounterClockwise();
                System.out.println(Main.extraTile.getRotation());

                BufferedImage extraTileImg = null;
                try{
                    extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.extraTile.makeFileName()));
                } catch (IOException e1){
                    e1.printStackTrace();
                }
                Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
                ImageIcon tileIcon = new ImageIcon(currentTileImage);

                extraTile.setIcon(tileIcon);

    		}
    	}
    }

    
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}