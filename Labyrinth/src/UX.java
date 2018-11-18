import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;

/**
 * Author: Satrajit Chatterjee
 */

public class UX {

    // Create a file to store current game data
    public void save(Tile[][] board) throws FileNotFoundException, UnsupportedEncodingException {

        Date date = new Date();
        String path = "Labyrinth/savefiles/" + (date.getYear()+1900) + "-" + date.getMonth() + "-" + date.getDate() + " " + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds() + ".txt";
        System.out.println(path);
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        // Loops through all of the rows
        for (int i = 0; i < 9; i++){

            // Loops through all of the columns
            for (int j = 0; j < 9; j++) {

                if (board[i][j] != null)
                    writer.println(board[i][j].toStorageString());
                else
                    writer.println("blank");

            }
        }

        for (int i = 0; i < 4; i++){
            writer.println(Main.deck.getPlayers().get(i).toStorageString());
        }

        writer.close();
    }

}
