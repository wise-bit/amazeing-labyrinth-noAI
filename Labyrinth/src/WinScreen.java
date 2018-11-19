/**
 * Author: Shrill Patel
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinScreen extends JFrame implements ActionListener {

    private String winText = "a-MAZE-ing! You Won!";
    private JLabel winLabel;
    private JButton closeScreen = new JButton("CLOSE");
    private JLabel picture2;
    private Font font = new Font("Sylfaen", Font.BOLD, 30);

    public WinScreen() throws IOException{
        setLayout(null);
        setBounds(0, 0, 400, 400);
        this.setTitle("aMAZEing Labyrinth");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Labyrinth/res/background.jpg")))));

        //Closes program if the exit option is clicked.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        winLabel = new JLabel(winText);
        winLabel.setForeground(Color.WHITE);
        winLabel.setFont(font);
        winLabel.setBounds(20, 10, 400, 35);
        add(winLabel);
        winLabel.setVisible(true);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Labyrinth/res/trophy.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Image currentImg = img.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(currentImg);

        picture2 = new JLabel(imgIcon);
        picture2.setBounds(100, 70, 200, 200);
        add(picture2);
        picture2.setVisible(true);

        closeScreen.setOpaque(false);
        closeScreen.setContentAreaFilled(false);
        closeScreen.setBorderPainted(false);
        closeScreen.setBorder(null);
        closeScreen.setFont(new Font("Segoe Script" , Font.BOLD, 30));
        closeScreen.setForeground(Color.WHITE);
        closeScreen.setBounds(145, 300, 100, 35);
        add(closeScreen);
        closeScreen.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == closeScreen)
            this.dispose();

    }
}
