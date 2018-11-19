/*
 Author: Shrill Patel
 Author: Lazar Glumac
 */

import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PlayerGUI extends JFrame implements ActionListener {

    //The components used to initialize the game(# of player and colur assignment)
    private JLabel title = new JLabel("Enter the Names of the Players:");
    private JLabel[] playerTitle = new JLabel[4];
    private Font font = new Font("Helvetica", Font.BOLD, 40);
    private ImageIcon[] dots = new ImageIcon[4];    //array to hold the player colours
    private JLabel[] playerColour = new JLabel[4];
    private JButton next = new JButton("Continue");
    private JLabel picture;

    //Constructor Method
    public PlayerGUI() throws IOException {

        //Makes new frame and sets its attributes
        setLayout(null);
        setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
        this.setTitle("aMAZEing Labyrinth");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Labyrinth/res/background.jpg")))));

        //Allows the program to be exited if the close button is clicked
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Adds the title player name
        title.setForeground(Color.WHITE);
        title.setFont(font);
        title.setBounds(818, 20, 600, 45);
        add(title);

        //Adds the continue button
        next.setOpaque(false);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);
        next.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 40));
        next.setForeground(Color.WHITE);
        next.setBounds(525, 650, 400, 75);
        add(next);
        next.addActionListener(this);

        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                next.setForeground(Color.WHITE);
                next.setFont(new Font("Segoe Script" , Font.BOLD, 70));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                next.setForeground(Color.WHITE);
                next.setFont(new Font("Segoe Script" , Font.BOLD, 40));
            }
        });

        playerOneSetup();
        playerTwoSetup();
        playerThreeSetup();
        playerFourSetup();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Labyrinth/res/maze.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Image currentImg = img.getScaledInstance(500,500, Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(currentImg);

        picture = new JLabel(imgIcon);
        picture.setBounds(80, 80, 500, 500);
        add(picture);
        picture.setVisible(true);

        //Makes all the attributes of the screen visible
        setVisible(true);
        // setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize(1440, 900);
        this.setLocation(Board.dim.width/2-this.getSize().width/2, Board.dim.height/2-this.getSize().height/2);
        repaint();
    }

    //Methods that are used to setup up to allow the user to enter the players information based on the number of players they select
    public void playerOneSetup() {
        dots[0] = new ImageIcon("Labyrinth/res/PlayerImages/reddot.png");
        playerColour[0] = new JLabel(dots[0]);
        playerColour[0].setBounds(850, 80, 80, 80);
        add(playerColour[0]);

        Main.names[0] = new JTextField(20);
        Main.names[0].setBounds(1100, 100, 250, 40);
        add(Main.names[0]);

        TextFieldListener tfListener = new TextFieldListener();
        Main.names[0].addActionListener(tfListener);

        //Main.playerNames[0].setPlayerName(Main.names[0].getText());

        playerTitle[0] = new JLabel("Player 1:");
        playerTitle[0].setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        playerTitle[0].setForeground(Color.WHITE);
        playerTitle[0].setBounds(950, 100, 150, 40);
        add(playerTitle[0]);
    }

    public void playerTwoSetup() {
        dots[1] = new ImageIcon("Labyrinth/res/PlayerImages/yellowdot.png");
        playerColour[1] = new JLabel(dots[1]);
        playerColour[1].setBounds(850, 200, 80, 80);
        add(playerColour[1]);

        Main.names[1] = new JTextField(20);
        Main.names[1].setBounds(1100, 220, 250, 40);
        add(Main.names[1]);

        TextFieldListener tfListener = new TextFieldListener();
        Main.names[1].addActionListener(tfListener);

        //Main.playerNames[1].setPlayerName(Main.names[1].getText());

        playerTitle[1] = new JLabel("Player 2:");
        playerTitle[1].setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        playerTitle[1].setForeground(Color.WHITE);
        playerTitle[1].setBounds(950, 220, 150, 40);
        add(playerTitle[1]);
    }

    public void playerThreeSetup() {
        dots[2] = new ImageIcon("Labyrinth/res/PlayerImages/bluedot.png");
        playerColour[2] = new JLabel(dots[2]);
        playerColour[2].setBounds(850, 320, 80, 80);
        add(playerColour[2]);

        Main.names[2] = new JTextField(20);
        Main.names[2].setBounds(1100, 340, 250, 40);
        add(Main.names[2]);

        TextFieldListener tfListener = new TextFieldListener();
        Main.names[2].addActionListener(tfListener);

       // Main.playerNames[2].setPlayerName(Main.names[2].getText());

        playerTitle[2] = new JLabel("Player 3:");
        playerTitle[2].setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        playerTitle[2].setForeground(Color.WHITE);
        playerTitle[2].setBounds(950, 340, 150, 40);
        add(playerTitle[2]);
    }

    public void playerFourSetup() {
        dots[3] = new ImageIcon("Labyrinth/res/PlayerImages/greendot.png");
        playerColour[3] = new JLabel(dots[3]);
        playerColour[3].setBounds(850, 440, 80, 80);
        add(playerColour[3]);

        Main.names[3] = new JTextField(20);
        Main.names[3].setBounds(1100, 460, 250, 40);
        add(Main.names[3]);

        TextFieldListener tfListener = new TextFieldListener();
        Main.names[3].addActionListener(tfListener);

        //Main.playerNames[3].setPlayerName(Main.names[3].getText());

        playerTitle[3] = new JLabel("Player 4:");
        playerTitle[3].setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        playerTitle[3].setForeground(Color.WHITE);
        playerTitle[3].setBounds(950, 460, 150, 40);
        add(playerTitle[3]);
    }

    // This method re adds the third player options to the screen if it has been detected they are missing
    private void reAddPlayerThreeSetup() {

        playerTitle[2].setBounds(950, 340, 150, 40);
        add(playerTitle[2]);
        playerColour[2].setBounds(850, 320, 80, 80);
        add(playerColour[2]);
        Main.names[2].setBounds(1100, 340, 250, 40);
        add(Main.names[2]);
      //  Main.playerNames[2].setPlayerName(Main.names[2].getText());
        repaint();
    }

    // This method re adds the fourth player options to the screen if it has been detected they are missing
    private void reAddPlayerFourSetup() {

        playerTitle[3].setBounds(950, 460, 150, 40);
        add(playerTitle[3]);
        playerColour[3].setBounds(850, 440, 80, 80);
        add(playerColour[3]);
        Main.names[3].setBounds(1100, 460, 250, 40);
        add(Main.names[3]);
     //   Main.playerNames[3].setPlayerName(Main.names[3].getText());
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == next) {
            Main.visibleNames[0] = Main.names[0].getText();
            Main.visibleNames[1] = Main.names[1].getText();
            Main.visibleNames[2] = Main.names[2].getText();
            Main.visibleNames[3] = Main.names[3].getText();
            System.out.println(Main.visibleNames[0]);
            System.out.println(Main.visibleNames[1]);
            System.out.println(Main.visibleNames[2]);
            System.out.println(Main.visibleNames[3]);

            this.setVisible(false);
            try {
                new GameGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // This method checks if a specific method is found on the frame/panel
    boolean isThisComponentFoundInJPanel(Component lookingComponent) {

        Component[] componentsArray = this.getContentPane().getComponents();

        for (Component component : componentsArray) {
            if (lookingComponent == component)
                return true;
        }
        return false;
    }

    private class TextFieldListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}