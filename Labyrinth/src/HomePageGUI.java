/*
 Author: Shrill Patel
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
    private JLabel gameTitle = new JLabel ("AMAZEING LABYRINTH");;
    private JButton start = new JButton ("START");;
    private JLabel picture1 = new JLabel (new ImageIcon("/Users/shrillpatel/IdeaProjects/SLS/Labyrinth/res/pic1.png"));
    private Font font = new Font("Helvetica" , Font.BOLD, 75);
//    Clip clip;
//    Clip clip2;

    //constructor method
    public HomePageGUI () throws IOException {

        //Makes frame and sets size and color (full-screen)
        setLayout(null);
        setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
        this.setTitle("aMAZEing Labyrinth");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("/Users/shrillpatel/IdeaProjects/SLS/Labyrinth/res/background.jpg")))));

        //Closes program if the exit option is clicked.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Adds the title and sets the font
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setFont(font);
        gameTitle.setBounds(292, 0, 855, 150);
        add(gameTitle);

        //Add picture to frame
        picture1.setBounds(425, 150, 590, 430);
        add(picture1);

        //Adds continue button and makes it clickable
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setFont(new Font("Helvetica" , Font.CENTER_BASELINE, 50));
        start.setForeground(Color.WHITE);
        start.setBounds(575, 625, 275, 120);
        add(start);
        start.addActionListener(this);

        //Makes every componet of the home screen visible
        setVisible(true);
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