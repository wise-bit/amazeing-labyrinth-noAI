import java.awt.*;
import java.awt.event.*;
//import java.io.*;
//import javax.sound.sampled.*;
import javax.swing.*;

public class HomePageGUI extends JFrame implements ActionListener {

    //Home Screen Components
    private JLabel gameTitle;
    private JButton start;
    private JLabel picture1;
    private Font font = new Font("Helvetica" , Font.BOLD, 75);
//    Clip clip;
//    Clip clip2;

    //constructor method
    public HomePageGUI () {

        //Makes frame and sets size and color (full-screen)
        setLayout(null);
        setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
        getContentPane().setBackground(new Color (34, 55, 218));

        //Closes program if the exit option is clicked.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Adds the title and sets the font
        gameTitle = new JLabel ("AMAZEING LABYRINTH");
        gameTitle.setForeground(new Color (47,79,79));
        gameTitle.setFont(font);
        gameTitle.setBounds(292, 0, 855, 150);
        add(gameTitle);

        //Add picture to frame
        picture1 = new JLabel (new ImageIcon("res/images/pic1.png"));
        picture1.setBounds(425, 150, 590, 430);
        add(picture1);

        //Adds continue button and makes it clickable
        start = new JButton ("START");
        start.setFont(new Font("Helvetica" , Font.CENTER_BASELINE, 50));
        start.setBounds(575, 625, 275, 120);
        start.setBackground(Color.RED);
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

    public void actionPerformed(ActionEvent event) {

        //If the button on home screen is pressed, close home frame
        if (event.getSource() == start) {
            this.setVisible(false);
            //clip.stop();
            //instructionsMusic();
            new PlayerSelectionGUI();

        }

        if (event.getSource() == start) {
            this.dispose();
            new PlayerSelectionGUI();

        }
    }
}