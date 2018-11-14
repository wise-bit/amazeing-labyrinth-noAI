/*
Author: Shrill and Satrajit
 */

import java.io.*;
import java.util.*;

public class Deck {

    private ArrayList<Player> players = new ArrayList<Player>();
    ExtraMethods extra = new ExtraMethods();
    private ArrayList<String> cardsList = extra.s.listOfTreasures();

    public Deck(int numOfPlayers) throws FileNotFoundException {

        for(int x = 0; x < numOfPlayers; x++){

            String name = "Name";
            String color = "";
            int row = 0;
            int column = 0;

            if (x == 0){
                color = "red";
                row = 1;
                column = 1;
            } else if (x == 1) {
                color = "blue";
                row = 7;
                column = 7;
            } else if (x == 2) {
                color = "green";
                row = 1;
                column = 7;
            } else if (x == 3) {
                color = "orange";
                row = 7;
                column = 1;
            }

            Player player = new Player(newDeck(), name, color, row, column);
            players.add(player);

        }

    }

    public String[] newDeck(){

        String[] arr = new String[5];

        for(int y = 0; y < 5; y++){
            arr[y] = cardsList.remove(0);
        }

        return arr;

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<String> getCardsList() {
        return cardsList;
    }

    public void setCardsList(ArrayList<String> cardsList) {
        this.cardsList = cardsList;
    }
}