import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

/**
 * Author: Satrajit Chatterjee
 */

public class UX {

    public static final JFileChooser chooser = new JFileChooser();

    // Create a file to store current game data
    public static void save() throws FileNotFoundException, UnsupportedEncodingException {

        Date date = new Date();
        String path = "Labyrinth/savefiles/" + (date.getYear()+1900) + "-" + date.getMonth() + "-" + date.getDate() + " " + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds() + ".txt";
        System.out.println(path);
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        // Loops through all of the rows
        for (int i = 0; i < 9; i++){
            // Loops through all of the columns
            for (int j = 0; j < 9; j++) {
                if (Main.board[i][j] != null)
                    writer.println(Main.board[i][j].toStorageString());
                else
                    writer.println("blank");
            }
        }
        // Stores all the players
        for (int i = 0; i < 4; i++){
            writer.println(Main.deck.players.get(i).toStorageString());
        }
        writer.close();
    }

    public static void load() throws IOException {

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files for loading game states", "txt");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File("Labyrinth/savefiles"));
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getPath();
            System.out.println(path);
            File f = new File(path);
            Scanner reader = new Scanner(f);
            int position = 0;
            while (reader.hasNextLine()){
                if (position < 81) {
                    String line = reader.nextLine();
                    if (line.equals("blank")) {
                        Main.board[position / 9][position % 9] = null;
                    } else {
                        String[] l = line.split(",");
                        Tile current = new Tile(l[0], Boolean.parseBoolean(l[1]), l[2].charAt(0), Integer.parseInt(l[3]), Integer.parseInt(l[4]), Integer.parseInt(l[5]));
                        Main.board[position / 9][position % 9] = current;
                    }
                } else if (position < 85){
                    String line = reader.nextLine();
                    String[] l = line.split(",");
                    String[] cards = l[0].split(":");
                    Player p = new Player(cards, l[1], l[2], Integer.parseInt(l[3]), Integer.parseInt(l[4]));
                    System.out.println(p);
                    p.setLocation(35 + 60*p.getColumns(), 10 + 60*p.getRows());
                    Main.deck.players.set(position-81, p);
                }
                position++;
            }
            new GameGUI();
        }

    }

}
