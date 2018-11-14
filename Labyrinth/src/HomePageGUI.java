/*
 * Author: Shrill Patel
 * Author: Satrajit Chatterjee
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
//import java.io.*;
//import javax.sound.sampled.*;
import javax.imageio.*;
import javax.swing.*;

public class HomePageGUI extends JFrame implements ActionListener {

    //Home Screen Components
    private JLabel gameTitle = new JLabel("A-MAZE-ING LABYRINTH", SwingConstants.CENTER);
    private JButton start = new JButton ("Start");;
    private JLabel picture1 = new JLabel (new ImageIcon("Labyrinth/res/pic1.png"));
    private Font font = new Font("Sylfaen", Font.BOLD, 80); // Freestyle Script, Matura MT Script Capitals, French Script MT

    // Used to get dimensions of Screen
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//    Clip clip;
//    Clip clip2;

//    public void paint(Graphics g){
//        g.drawString("Hello to JavaTutorial.net", 100, 100);
//    }

    //constructor method
    public HomePageGUI () throws IOException, FontFormatException {

        //Makes frame and sets size and color (full-screen)
        setLayout(null);
        setBounds(0, 0, dim.width, dim.height);
        this.setTitle("aMAZEing Labyrinth");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Labyrinth/res/background.jpg")))));

        //Closes program if the exit option is clicked.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameTitle.setForeground(Color.WHITE);
        gameTitle.setFont(font);
        gameTitle.setSize(dim.width,(int) (dim.height/5));
        add(gameTitle);
        gameTitle.setVisible(true);

//        try{
//            Font font = Font.createFont(Font.TRUETYPE_FONT, HomePageGUI.class.getResourceAsStream("Labyrinth/res/Bikarosta-Script.ttf"));
//            gameTitle.setFont(font.deriveFont(Font.BOLD, 12f));
//        }
//        catch(Exception e){
//            System.out.println("\n"+e);
//        }

        //Add picture to frame
        picture1.setBounds(dim.width/5, dim.height/5, dim.width - 2*dim.width/5, dim.height - 2*dim.height/5);
        add(picture1);

        //Adds continue button and makes it clickable
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setBorder(null);
        start.setFont(new Font("Segoe Script" , Font.PLAIN, 50));
        start.setForeground(Color.WHITE);
        // start.setBackground(new Color(59, 89, 182));
        start.setBounds(582, 670, 275, 120);
        add(start);
        start.addActionListener(this);

        // Makes button bigger when hovered over
        start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                start.setForeground(Color.WHITE);
                start.setFont(new Font("Segoe Script" , Font.PLAIN, 80));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                start.setForeground(Color.WHITE);
                start.setFont(new Font("Segoe Script" , Font.PLAIN, 50));
            }
        });

        //Makes every componet of the home screen visible
        setVisible(true);
        // setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize(1440, 900);
//      homeScreenMusic();
        repaint();

    }

//    //A method for the sound when game starts
//    public void homeScreenMusic () {
//
//        //checks if the sound file exists
//        try {
//
//            //convert to .wav
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/Pac-Man_Fever.wav").getAbsoluteFile());		//imports the sound file
//            clip = AudioSystem.getClip();
//            clip.open(audioInputStream);		//opens the clip
//            clip.start();		//starts playing the clip
//
//            //if file is invalid
//        } catch(Exception  ex) {
//            System.out.println("Error with playing sound");
//
//        }
//    }

    //Method used to listen to events occuring on the current frame
    public void actionPerformed(ActionEvent event) {

        //If the button on home screen is pressed, close home frame
        if (event.getSource() == start) {
            this.setVisible(false);
            //clip.stop();
            //instructionsMusic();
            try {
                new PlayerGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}