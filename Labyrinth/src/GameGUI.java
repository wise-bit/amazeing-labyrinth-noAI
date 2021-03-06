
/**
 * Author: Shrill Patel
 * Author: Lazar Glumac
 * Author: Satrajit Chatterjee
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameGUI extends JFrame implements ActionListener, MouseListener {

    private JLabel fixedBoard;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu help = new JMenu("Help");
    private JMenuItem instructions = new JMenuItem("Instructions");
    private JMenu file = new JMenu("File");
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem load = new JMenuItem("Load");
    private Font font = new Font("Helvetica", Font.BOLD, 40);
    private Font font2 = new Font("Helvetica", Font.BOLD, 25);
    private Player[] player = new Player[4];
    private ImageIcon[] dots = new ImageIcon[4];
    private JLabel[] playerColour = new JLabel[4];
    private JLabel[] playerName = new JLabel[4];
    private int currentPlayer = 0;
    private boolean hasSlided = false;

    private ArrayList<JLabel> playerOneLabels = new ArrayList<JLabel>();
    private ArrayList<JLabel> playerTwoLabels = new ArrayList<JLabel>();
    private ArrayList<JLabel> playerThreeLabels = new ArrayList<JLabel>();
    private ArrayList<JLabel> playerFourLabels = new ArrayList<JLabel>();

    private JLabel[] playerScoreName = new JLabel[4];
    private JLabel[] playerScore = new JLabel[4];

    private JButton[] topButtons = new JButton[3];
    private JButton[] bottomButtons = new JButton[3];
    private JButton[] leftButtons = new JButton[3];
    private JButton[] rightButtons = new JButton[3];

    private JLabel extraTileLabel = new JLabel();

    public GameGUI() throws IOException {

        setLayout(null);
        setBounds(0, 0, 1440, 900);
        this.setTitle("aMAZEing Labyrinth");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Labyrinth/res/background.jpg")))));

        // setContentPane(fixedBoard);

        // Creates a scaleable image
        BufferedImage img = null;
        try {
            // img = ImageIO.read(new File("Labyrinth/res/white.jpg"));
            img = ImageIO.read(new File("Labyrinth/res/blogamazeingBoard.jpg")); // TODO: Switch Back
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(900/2, 900/2, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        fixedBoard = new JLabel(imageIcon);
        fixedBoard.setBounds(1440/3,900/10,900/2,900/2);
        fixedBoard.setVisible(true);
        add(fixedBoard);
        // Main fixed Board image ends here

        //Creates the menu bar
//        setJMenuBar(menuBar);
//        menuBar.add(help);
//        menuBar.add(file);
//        help.add(instructions);
//        file.add(save);
//        file.add(load);
        createMenuBar();

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

        Main.extraTile.addMouseListener(this);

        //////////////////////////////////////////////////
        //Make player #1 name show up
        playerName[0] = new JLabel(Main.visibleNames[0]);
        playerName[0].setForeground(Color.WHITE);
        playerName[0].setFont(font);
        playerName[0].setBounds(100, 10, 200, 80);
        add(playerName[0]);
        playerName[0].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #2 name show up
        playerName[1] = new JLabel(Main.visibleNames[1]);
        playerName[1].setForeground(Color.WHITE);
        playerName[1].setFont(font);
        playerName[1].setBounds(100, 310, 200, 80);
        add(playerName[1]);
        playerName[1].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #3 name show up
        playerName[2] = new JLabel(Main.visibleNames[2]);
        playerName[2].setForeground(Color.WHITE);
        playerName[2].setFont(font);
        playerName[2].setBounds(1100, 10, 200, 80);
        add(playerName[2]);
        playerName[2].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #4 name show up
        playerName[3] = new JLabel(Main.visibleNames[3]);
        playerName[3].setForeground(Color.WHITE);
        playerName[3].setFont(font);
        playerName[3].setBounds(1100, 310, 200, 80);
        add(playerName[3]);
        playerName[3].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #1 score show up
        playerScore[0] = new JLabel(Integer.toString(Main.deck.players.get(0).getTreasures()));
        playerScore[0].setForeground(Color.WHITE);
        playerScore[0].setFont(font);
        playerScore[0].setBounds(200, 578, 200, 80);
        add(playerScore[0]);
        playerScore[0].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #2 score show up
        playerScore[1] = new JLabel(Integer.toString(Main.deck.players.get(1).getTreasures()));
        playerScore[1].setForeground(Color.WHITE);
        playerScore[1].setFont(font);
        playerScore[1].setBounds(235, 655, 200, 80);
        add(playerScore[1]);
        playerScore[1].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #3 score show up
        playerScore[2] = new JLabel(Integer.toString(Main.deck.players.get(2).getTreasures()));
        playerScore[2].setForeground(Color.WHITE);
        playerScore[2].setFont(font);
        playerScore[2].setBounds(1300, 575, 250, 80);
        add(playerScore[2]);
        playerScore[2].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #4 score show up
        playerScore[3] = new JLabel(Integer.toString(Main.deck.players.get(3).getTreasures()));
        playerScore[3].setForeground(Color.WHITE);
        playerScore[3].setFont(font);
        playerScore[3].setBounds(1320, 655, 250, 80);
        add(playerScore[3]);
        playerScore[3].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #1 name (for score) show up
        playerScoreName[0] = new JLabel("Red Dot Score:");
        playerScoreName[0].setForeground(Color.WHITE);
        playerScoreName[0].setFont(font2);
        playerScoreName[0].setBounds(10, 600, 250, 30);
        add(playerScoreName[0]);
        playerScoreName[0].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #2 name (for score) show up
        playerScoreName[1] = new JLabel("Yellow Dot Score:");
        playerScoreName[1].setForeground(Color.WHITE);
        playerScoreName[1].setFont(font2);
        playerScoreName[1].setBounds(10, 680, 250, 30);
        add(playerScoreName[1]);
        playerScoreName[1].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #3 name (for score) show up
        playerScoreName[2] = new JLabel("Blue Dot Score:");
        playerScoreName[2].setForeground(Color.WHITE);
        playerScoreName[2].setFont(font2);
        playerScoreName[2].setBounds(1100, 575, 250, 80);
        add(playerScoreName[2]);
        playerScoreName[2].setVisible(true);
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //Make player #4 name (for score) show up
        playerScoreName[3] = new JLabel("Green Dot Score:");
        playerScoreName[3].setForeground(Color.WHITE);
        playerScoreName[3].setFont(font2);
        playerScoreName[3].setBounds(1100, 655, 250, 80);
        add(playerScoreName[3]);
        playerScoreName[3].setVisible(true);
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
        for(int x = 0; x < 5; x++){

            BufferedImage picOne = null;
            try {
                picOne = ImageIO.read(new File("Labyrinth/res/cards/" + Main.deck.players.get(0).getPlayerHand()[x] + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image currentHandOne = picOne.getScaledInstance(80,150, Image.SCALE_SMOOTH);
            ImageIcon cardIconOne = new ImageIcon(currentHandOne);

            JLabel cardOne = new JLabel(cardIconOne);
            cardOne.setBounds(10 + (x * 70), 100, 80, 150);
            playerOneLabels.add(cardOne);
        }

        for(int x = 0; x < 5; x++){
            add(playerOneLabels.get(x));
        }
        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Dealing the hand out for player TWO
        for(int y = 0; y < 5; y++){

            BufferedImage picTwo = null;
            try {
                picTwo = ImageIO.read(new File("Labyrinth/res/cards/" + Main.deck.players.get(1).getPlayerHand()[y] + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image currentHandTwo = picTwo.getScaledInstance(80,150, Image.SCALE_SMOOTH);
            ImageIcon cardIconTwo = new ImageIcon(currentHandTwo);

            JLabel cardTwo = new JLabel(cardIconTwo);
            cardTwo.setBounds(10 + (y * 70), 400, 80, 150);
            playerTwoLabels.add(cardTwo);
        }

        for(int y = 0; y < 5; y++){
            add(playerTwoLabels.get(y));
        }
        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        //Dealing the hand out for player THREE
        for(int i = 0; i < 5; i++){

            BufferedImage picThree = null;
            try {
                picThree = ImageIO.read(new File("Labyrinth/res/cards/" + Main.deck.players.get(2).getPlayerHand()[i] + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image currentHandThree = picThree.getScaledInstance(80,150, Image.SCALE_SMOOTH);
            ImageIcon cardIconThree = new ImageIcon(currentHandThree);

            JLabel cardThree = new JLabel(cardIconThree);
            cardThree.setBounds(1000 + (i * 70), 100, 80, 150);
            playerThreeLabels.add(cardThree);
        }

        for (int i = 0; i < 5; i++) {
            add(playerThreeLabels.get(i));
        }
        ////////////////////////////////////////////////////

        // System.out.println(Main.deck.players.get(0));
        // Main.deck.players.get(0).setIcon(playerFourIcon);
        // Main.deck.players.get(0).setBounds(10, fixedBoard.getY()-10, 50, 50);
        // fixedBoard.add(Main.deck.players.get(0));
        // Main.deck.players.get(0).setVisible(true);

        ////////////////////////////////////////////////////
        //Dealing the hand out for player FOUR
        for(int z = 0; z < 5; z++){

            BufferedImage picFour = null;
            try {
                picFour = ImageIO.read(new File("Labyrinth/res/cards/" + Main.deck.players.get(3).getPlayerHand()[z] + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image currentHandFour = picFour.getScaledInstance(80,150, Image.SCALE_SMOOTH);
            ImageIcon cardIconFour = new ImageIcon(currentHandFour);

            JLabel card = new JLabel(cardIconFour);
            card.setBounds(1000 + (z * 70), 400, 80, 150);
            playerFourLabels.add(card);
        }

        for (int z = 0; z < 5; z++) {
            add(playerFourLabels.get(z));
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

        extraTileLabel.setIcon(tileIcon);

        extraTileLabel.setBounds(685, 600, 100, 100);
        extraTileLabel.addMouseListener(this);
        add(extraTileLabel);
        setVisible(true);

        System.out.println(Main.deck.players.get(0).getPlayerName());

        //Closes program if the exit option is clicked.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addDots();
        setVisible(true);
        // setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize(1440, 900);
        this.setLocation(Board.dim.width/2-this.getSize().width/2, Board.dim.height/2-this.getSize().height/2);
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
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Instructions();
            }
        });

        JMenu file = new JMenu("File");
        menuBar.add(file);

        // ImageIcon saveIcon = new ImageIcon("Labyrinth/res/save-icon.png").getImage().getScaledInstance(10,10,Image.SCALE_DEFAULT);
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                try {
                    UX.save();
                } catch (Exception easd){
                    System.out.println(easd);
                }
            }
        });

        JMenuItem load = new JMenuItem("Load");
        file.add(load);
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                try {
                    UX.load();
                } catch (Exception easd){
                    System.out.println(easd);
                }
            }
        });

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

        if (!hasSlided) {

            hasSlided = true;

            for (int i = 0; i < 3; i++) {

                if (e.getSource() == topButtons[i]) {

                    Board.shiftBoardTiles(0, 2 + 2 * i, 4);

                    binaryBoardPrinter();
                    Setup.fullBinaryBoard();
                    System.out.println();

                    Main.board[1][2 + 2 * i].setBounds(18 + 60 * (1 + 2 * i), 22, 50, 50);
                    fixedBoard.add(Main.board[1][2 + 2 * i]);

                    repaint();

                } else if (e.getSource() == bottomButtons[i]) {

                    Board.shiftBoardTiles(8, 2 + 2 * i, 3);

                    binaryBoardPrinter();
                    Setup.fullBinaryBoard();
                    System.out.println();

                    Main.board[7][2 + 2 * i].setBounds(18 + 60 * (1 + 2 * i), 382, 50, 50);
                    fixedBoard.add(Main.board[7][2 + 2 * i]);

                    repaint();

                } else if (e.getSource() == rightButtons[i]) {

                    Board.shiftBoardTiles(2 + 2 * i, 8, 2);

                    binaryBoardPrinter();
                    Setup.fullBinaryBoard();
                    System.out.println();

                    Main.board[2 + 2 * i][7].setBounds(378, 22 + 60 * (1 + 2 * i), 50, 50);
                    fixedBoard.add(Main.board[2 + 2 * i][7]);

                    repaint();

                } else if (e.getSource() == leftButtons[i]) {

                    Board.shiftBoardTiles(2 + 2 * i, 0, 1);

                    binaryBoardPrinter();
                    Setup.fullBinaryBoard();
                    System.out.println();

                    Main.board[2 + 2 * i][1].setBounds(18, 22 + 60 * (1 + 2 * i), 50, 50);
                    fixedBoard.add(Main.board[2 + 2 * i][1]);

                    repaint();

                }

            }

            BufferedImage extraTileImg = null;
            try {
                extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.extraTile.makeFileName()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Image currentTileImage = extraTileImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon tileIcon = new ImageIcon(currentTileImage);

            extraTileLabel.setIcon(tileIcon);
        } else {

            JOptionPane.showMessageDialog(null, "Invalid move, sir!", "Information!!!!!", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int currentRow = Main.deck.players.get(currentPlayer).getRows();
        int currentColumn = Main.deck.players.get(currentPlayer).getColumns();
        int[][] modifyableBoard = new int[27][27];


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == Main.board[i][j]) {

                    System.out.println(Main.board[i][j] + " was clicked :: " + Main.board[i][j].getLocation());
                    System.out.println();

                    // This copies the arrays
                    for (int q = 0; q < 27; q++){
                        for (int w = 0; w < 27; w++) {
                            modifyableBoard[q][w] = Main.binary[q][w];
                        }
                    }

                    if (Main.deck.players.get(currentPlayer).movePlayer(modifyableBoard, i,j, currentRow, currentColumn, 0) == true){

                        String whatever = Main.board[i][j].getName();

                        try {
                            switch (currentPlayer){
                                case 0:
                                    int index = Arrays.asList(Main.deck.players.get(currentPlayer).getPlayerHand()).indexOf(whatever);
                                    playerOneLabels.get(index).setVisible(false);
                                    System.out.println(playerOneLabels.get(index).isVisible());
                                    break;
                                case 1:
                                    playerTwoLabels.get(Arrays.asList(Main.deck.players.get(currentPlayer).getPlayerHand()).indexOf(whatever)).setVisible(false);
                                    break;
                                case 2:
                                    playerThreeLabels.get(Arrays.asList(Main.deck.players.get(currentPlayer).getPlayerHand()).indexOf(whatever)).setVisible(false);
                                    break;
                                case 3:
                                    playerFourLabels.get(Arrays.asList(Main.deck.players.get(currentPlayer).getPlayerHand()).indexOf(whatever)).setVisible(false);
                                    break;
                            }
                        } catch (Exception exx){

                        }

                        Main.deck.players.get(currentPlayer).removeCard(whatever);
                        playerScore[currentPlayer].setText(Integer.toString(Main.deck.players.get(currentPlayer).getTreasures()));

                        if(Main.deck.players.get(currentPlayer).getTreasures() == 5) {
                            try {
                                new WinScreen();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                        //////////
                        Main.board[currentRow][currentColumn].remove(Main.deck.players.get(currentPlayer));
                        Main.board[currentRow][currentColumn].repaint();
                        Main.deck.players.get(currentPlayer).setLocation(0,0);
                        //////////////

                        Main.deck.players.get(currentPlayer).setRows(i);
                        Main.deck.players.get(currentPlayer).setColumns(j);
                        Main.deck.players.get(currentPlayer).setBounds(15, -15, 80, 80);

                        Main.board[i][j].add(Main.deck.players.get(currentPlayer));
                        Main.board[i][j].repaint();

                        System.out.println("Working: " + i + "," + j + " --> " + Main.board[i][j].getLocation());
                        if (currentPlayer == 3) currentPlayer = 0;
                        else currentPlayer++;
                        hasSlided = false;
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

        if (e.getSource() == extraTileLabel) {

            if (e.getButton() == MouseEvent.BUTTON1) {

                Main.extraTile.rotateClockwise();

                BufferedImage extraTileImg = null;
                try {
                    extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.extraTile.makeFileName()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
                ImageIcon tileIcon = new ImageIcon(currentTileImage);

                extraTileLabel.setIcon(tileIcon);

            }
            else if (e.getButton() == MouseEvent.BUTTON3) {

                Main.extraTile.rotateCounterClockwise();

                BufferedImage extraTileImg = null;
                try {
                    extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.extraTile.makeFileName()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
                ImageIcon tileIcon = new ImageIcon(currentTileImage);

                extraTileLabel.setIcon(tileIcon);

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
