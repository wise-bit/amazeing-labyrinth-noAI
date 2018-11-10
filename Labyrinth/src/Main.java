/*
Author: Satrajit's version
 */

import java.io.FileNotFoundException;

public class Main {
  
    public static void main(String[] args) throws FileNotFoundException {

        Player[] players = new Player[4];
        Setup s = new Setup();

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                System.out.println(s.getBoard()[i][j]);
            }
        }
      
        // new HomePageGUI();

    }
  
}
