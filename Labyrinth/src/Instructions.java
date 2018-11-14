import javax.swing.*;

public class Instructions extends JFrame{

    private String instructions = "The players of Labyrinth must search the Labyrinth for your magical " +
            "objects and characters by carefully moving through the constantly changing maze. " +
            "The first player to find all of their objects and characters and then return to the " +
            "starting square is the winner of Labyrinth. On your turn, look at the top card in your " +
            "stack without showing it to the other players. You now have to try and get to the square " +
            "showing the same picture as on your card. To do this, first insert a maze card and move your " +
            "player.";
    private JTextArea textArea = new JTextArea(10, 20);
    private JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JDialog dialog = new JDialog();
    private JOptionPane optionPane = new JOptionPane();

    public Instructions() {
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        optionPane.add(scrollPane);
        setVisible(true);
    }
}