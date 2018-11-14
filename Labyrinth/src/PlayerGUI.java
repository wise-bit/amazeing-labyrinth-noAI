/*
 Author: Shrill Patel
 Author: Lazar Glumac
 */

import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PlayerGUI extends JFrame implements ActionListener {

    //The components used to initialize the game(# of player and colur assignment)
    private JLabel title1 = new JLabel("Chose the Number of Players:");
    private JLabel title2 = new JLabel("Enter the Names of the Players:");
    private JLabel[] playerTitle = new JLabel[4];
    private JButton twoPlayers = new JButton("2 Players");
    private JButton threePlayers = new JButton("3 Players");
    private JButton fourPlayers = new JButton("4 Players");
    private Font font = new Font("Helvetica", Font.BOLD, 40);
    private ImageIcon[] dots = new ImageIcon[4];    //array to hold the player colours
    private JLabel[] playerColour = new JLabel[4];
    private JTextField[] names = new JTextField[4];
    private JButton next = new JButton("Continue");

    //Constructor Method
    public PlayerGUI() throws IOException {

        //Makes new frame and sets its attributes
        setLayout(null);
        setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
        this.setTitle("aMAZEing Labyrinth");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Labyrinth/res/background.jpg")))));

        //Allows the program to be exited if the close button is clicked
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Adds the title for player selection
        title1.setForeground(Color.WHITE);
        title1.setFont(font);
        title1.setBounds(20, 20, 575, 45);
        add(title1);

        //Two Player Option
        twoPlayers.setOpaque(false);
        twoPlayers.setContentAreaFilled(false);
        twoPlayers.setBorderPainted(false);
        twoPlayers.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 40));
        twoPlayers.setForeground(Color.WHITE);
        twoPlayers.setBounds(188, 150, 220, 50);
        add(twoPlayers);
        twoPlayers.addActionListener(this);

        //Three Player Option
        threePlayers.setOpaque(false);
        threePlayers.setContentAreaFilled(false);
        threePlayers.setBorderPainted(false);
        threePlayers.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 40));
        threePlayers.setForeground(Color.WHITE);
        threePlayers.setBounds(188, 280, 220, 50);
        add(threePlayers);
        threePlayers.addActionListener(this);

        //Three Player Option
        fourPlayers.setOpaque(false);
        fourPlayers.setContentAreaFilled(false);
        fourPlayers.setBorderPainted(false);
        fourPlayers.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 40));
        fourPlayers.setForeground(Color.WHITE);
        fourPlayers.setBounds(188, 410, 220, 50);
        add(fourPlayers);
        fourPlayers.addActionListener(this);

        //Adds the title player name
        title2.setForeground(Color.WHITE);
        title2.setFont(font);
        title2.setBounds(818, 20, 600, 45);
        add(title2);

        //Adds the continue button
        next.setOpaque(false);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);
        next.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 40));
        next.setForeground(Color.WHITE);
        next.setBounds(609, 700, 220, 50);
        add(next);
        next.addActionListener(this);

        playerOneSetup();
        playerTwoSetup();
        playerThreeSetup();
        playerFourSetup();

        //Makes all the attributes of the screen visible
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        repaint();
    }

    //Methods that are used to setup up to allow the user to enter the players information based on the number of players they select
    public void playerOneSetup() {
        dots[0] = new ImageIcon("Labyrinth/res/PlayerImages/bluedot.png");
        playerColour[0] = new JLabel(dots[0]);
        playerColour[0].setBounds(850, 80, 80, 80);
        add(playerColour[0]);

        names[0] = new JTextField(20);
        names[0].setBounds(1100, 100, 250, 40);
        add(names[0]);

        playerTitle[0] = new JLabel("Player 1:");
        playerTitle[0].setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        playerTitle[0].setForeground(Color.WHITE);
        playerTitle[0].setBounds(950, 100, 150, 40);
        add(playerTitle[0]);
    }

    public void playerTwoSetup() {
        dots[1] = new ImageIcon("Labyrinth/res/PlayerImages/greendot.png");
        playerColour[1] = new JLabel(dots[1]);
        playerColour[1].setBounds(850, 200, 80, 80);
        add(playerColour[1]);

        names[1] = new JTextField(20);
        names[1].setBounds(1100, 220, 250, 40);
        add(names[1]);

        playerTitle[1] = new JLabel("Player 2:");
        playerTitle[1].setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        playerTitle[1].setForeground(Color.WHITE);
        playerTitle[1].setBounds(950, 220, 150, 40);
        add(playerTitle[1]);
    }

    public void playerThreeSetup() {
        dots[2] = new ImageIcon("Labyrinth/res/PlayerImages/yellowdot.png");
        playerColour[2] = new JLabel(dots[2]);
        playerColour[2].setBounds(850, 320, 80, 80);
        add(playerColour[2]);

        names[2] = new JTextField(20);
        names[2].setBounds(1100, 340, 250, 40);
        add(names[2]);

        playerTitle[2] = new JLabel("Player 3:");
        playerTitle[2].setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        playerTitle[2].setForeground(Color.WHITE);
        playerTitle[2].setBounds(950, 340, 150, 40);
        add(playerTitle[2]);
    }

    public void playerFourSetup() {
        dots[3] = new ImageIcon("Labyrinth/res/PlayerImages/reddot.png");
        playerColour[3] = new JLabel(dots[3]);
        playerColour[3].setBounds(850, 440, 80, 80);
        add(playerColour[3]);

        names[3] = new JTextField(20);
        names[3].setBounds(1100, 460, 250, 40);
        add(names[3]);

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
        names[2].setBounds(1100, 340, 250, 40);
        add(names[2]);

        repaint();
    }

    // This method re adds the fourth player options to the screen if it has been detected they are missing
    private void reAddPlayerFourSetup() {

        playerTitle[3].setBounds(950, 460, 150, 40);
        add(playerTitle[3]);
        playerColour[3].setBounds(850, 440, 80, 80);
        add(playerColour[3]);
        names[3].setBounds(1100, 460, 250, 40);
        add(names[3]);

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == twoPlayers) {

            for (int i = 2; i < 4; i++) {

                remove(playerTitle[i]);
                remove(playerColour[i]);
                remove(names[i]);
            }

        } else if (event.getSource() == threePlayers) {

            if (isThisComponentFoundInJPanel(playerTitle[2])) {

                remove(playerTitle[3]);
                remove(playerColour[3]);
                remove(names[3]);

            } else {
                reAddPlayerThreeSetup();
            }

        } else if (event.getSource() == fourPlayers) {

            if (isThisComponentFoundInJPanel(playerTitle[3]) == false) {

                reAddPlayerThreeSetup();
                reAddPlayerFourSetup();
            }
        }

        repaint();

        if (event.getSource() == next) {
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
}