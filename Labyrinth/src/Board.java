import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/*
 * Author: Satrajit Chatterjee
 * Author: Lazar Glumac
 */


public class Board {

	public static Tile[][] set;
	private int[][] tileset;

	private Player[] playersArray;

    // Runs setup once
    // Setup s = new Setup();

	private static final int RIGHT = 1;
	private static final int LEFT = 2;
	private static final int UP = 3;
	private static final int DOWN = 4;
	
	private static Tile tileForPlayer;

	public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	public Board() throws FileNotFoundException{
		Setup.binaryBoardPrinter();
		set = Main.board;
        makeDeck(4);
		//displayTilesStart();

		Tile[][] set = Main.board;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (set[i][j] != null && set[i][j].isMoveable()) {
                    System.out.println(set[i][j].makeFileName());
                }
            }
        }
	}

    private void displayTilesStart() {

    	for (int i = 0; i < Main.board.length; i++) {
    		
    		for (int j = 0; j < Main.board[i].length; j++) {
    			
    		//	Main.board[i][j].setBounds(r);
    		}
    	}
	}

	public static void shiftBoardTiles(int rowToShift, int columnToShift, int directionOfShift){

		// Checks if any player is on the row/column being shifted (depending on direction of shift)
		//checkTileOccupied(rowToShift, columnToShift, directionOfShift);

		if (directionOfShift == RIGHT){

            Main.board[rowToShift][0] = Main.extraTile;

            Main.board[rowToShift][0].setRow(rowToShift);
            Main.board[rowToShift][0].setColumn(0);
			
			set[rowToShift][8] = set[rowToShift][7];
			
			for (int i = 7; i >=  1; i--) {

                Main.board[rowToShift][i] = Main.board[rowToShift ][i - 1];
                Main.board[rowToShift][i].setColumn(i);

                Main.board[rowToShift][i].setLocation(Main.board[rowToShift][i].getX() + 50, Main.board[rowToShift][i].getY());

			}
			
			Main.extraTile = Main.board[rowToShift][8];

            Main.board[rowToShift][8] = null;
            Main.board[rowToShift][0] = null;
		}
		else if (directionOfShift == LEFT){

            Main.board[rowToShift][8] = Main.extraTile;

            Main.board[rowToShift][8].setRow(rowToShift);
            Main.board[rowToShift][8].setColumn(8);


            Main.board[rowToShift][0] = Main.board[rowToShift][1];
			
			for (int i = 1; i <=  7; i++) {

                Main.board[rowToShift][i] = Main.board[rowToShift][i + 1];
                Main.board[rowToShift][i].setColumn(i);
                Main.board[rowToShift][i].setLocation(Main.board[rowToShift][i].getX() - 50, Main.board[rowToShift][i].getY());

			}

			Main.extraTile = Main.board[rowToShift][0];

            Main.board[rowToShift][8] = null;
            Main.board[rowToShift][0] = null;
		}
		else if (directionOfShift == UP) {

            Main.board[8][columnToShift] = Main.extraTile;
			//System.out.println(getTileForPlayer().makeFileName());

            Main.board[8][columnToShift].setRow(8);
            Main.board[8][columnToShift].setColumn(columnToShift);

            //Main.board[0][columnToShift] = Main.board[1][columnToShift];
			
			for (int i = 0; i <= 7; i++) {

                Main.board[i][columnToShift] = Main.board[i + 1][columnToShift];
                Main.board[i][columnToShift].setRow(i);

                Main.board[i][columnToShift].setLocation(Main.board[i][columnToShift].getX(), Main.board[i][columnToShift].getY() - 58);

			}
			Main.extraTile = Main.board[0][columnToShift];

			// Main.board[0][columnToShift].setVisible(false);
			// Main.board[8][columnToShift].setVisible(false);
			Main.board[0][columnToShift] = null;
			Main.board[8][columnToShift] = null;
		}
		else if (directionOfShift == DOWN) {

            Main.board[0][columnToShift] = Main.extraTile;

            Main.board[0][columnToShift].setRow(0);
            Main.board[0][columnToShift].setColumn(columnToShift);
            
            Main.board[8][columnToShift] = Main.board[7][columnToShift];

			for (int i = 7; i >= 1; i--) {

                Main.board[i][columnToShift] = Main.board[i - 1][columnToShift];
                Main.board[i][columnToShift].setRow(i);

                Main.board[i][columnToShift].setLocation(Main.board[i][columnToShift].getX(), Main.board[i][columnToShift].getY() + 58);
			}

			Main.extraTile = Main.board[8][columnToShift];

            Main.board[0][columnToShift] = null;
            Main.board[8][columnToShift] = null;
		}
		
		//checkIfPlayerOverflowed();

	}

	public void checkIfPlayerOverflowed() {
		
		// Check if a player has been pushed outside the playable board, returns them to the appropriate row and column
		for (int i = 0; i < playersArray.length; i++) {
				
				if (playersArray[i].getColumns() == 8)
					playersArray[i].setColumns(1);
				else if (playersArray[i].getColumns() == 0)
					playersArray[i].setColumns(7);
				else if (playersArray[i].getRows() == 8)
					playersArray[i].setRows(1);
				else if (playersArray[i].getRows() == 0)
					playersArray[i].setRows(7);
				
		}
		
	}
	 
	private void checkTileOccupied(int rowIndex, int columnIndex, int directionOfShift) {

		// Checks if any player is on the tile being moved
		for (int i = 0; i < playersArray.length; i++) {

			int playerColumn = playersArray[i].getColumns();
			int playerRow = playersArray[i].getRows();

			if (directionOfShift == RIGHT) {
				
				if (playerRow == rowIndex) 
					playersArray[i].setColumns(playerColumn + 1);
			}
			else if (directionOfShift == LEFT) {
				
				if (playerRow == rowIndex)
					playersArray[i].setColumns(playerColumn - 1); 
			}
			else if (directionOfShift == UP) {
				
				if (playerColumn == columnIndex)
					playersArray[i].setRows(rowIndex - 1);
			}
			else if (directionOfShift == DOWN) {
				
				if (playerColumn == columnIndex)
					playersArray[i].setRows(rowIndex + 1);
			}
			
		}
		
	}
	public int[][] getTileset() {
		return tileset;
	}

	public void setTileset(int[][] tileset) {
		// this.tileset = tileset;

		for (Tile[] row : Main.board){
			for (Tile column : row){
				// int[][] t = Tile.getShape();
			}
		}

	}

	public static void setTileForPlayer(Tile tile) {

		tileForPlayer = tile;
	}

	public static Tile getTileForPlayer() {

		return tileForPlayer;
	}
	public Tile[][] getSet() {
		return Main.board;
	}

	public void setSet(Tile[][] set) {
		this.set = Main.board;
	}

	public Dimension getDim() {
		return dim;
	}

	public void setDim(Dimension dim) {
		this.dim = dim;
	}

    public void setDeck(Deck deck) {
        Main.deck = deck;
    }

    public Deck getDeck() {
        return Main.deck;
    }

    public void makeDeck(int players) throws FileNotFoundException {
        Main.deck = new Deck(players);
    }
}
