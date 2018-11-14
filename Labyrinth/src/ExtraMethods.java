/**
 * This Method is made for the sole purpose of debugging. Nothing to do with real game. Do not put any important methods in here
 */


import java.awt.GraphicsEnvironment;
import java.io.FileNotFoundException;

public class ExtraMethods {

    Setup s = new Setup();
    Tile[][] set = s.getBoard();
    Deck deck;

    public ExtraMethods() throws FileNotFoundException {
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public void makeDeck(int players) throws FileNotFoundException {
        deck = new Deck(players);
    }

    public void listFonts() {
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for ( int i = 0; i < fonts.length; i++ ) {
            System.out.println(fonts[i]);
        }
    }

}
