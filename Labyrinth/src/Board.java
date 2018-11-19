/*
 * Author: Satrajit Chatterjee
 * Author: Lazar Glumac
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Board {

	public static Tile[][] set;
	private int[][] tileset;

	// Runs setup once
	// Setup s = new Setup();

	private static final int RIGHT = 1;
	private static final int LEFT = 2;
	private static final int UP = 3;
	private static final int DOWN = 4;

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

	public static void shiftBoardTiles(int rowToShift, int columnToShift, int directionOfShift){

		// Checks if any player is on the row/column being shifted (depending on direction of shift)

		 checkTileOccupied(rowToShift, columnToShift, directionOfShift);

		if (directionOfShift == RIGHT){

			Main.board[rowToShift][0] = Main.extraTile;

			BufferedImage extraTileImg = null;

			try {
				extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.board[rowToShift][0].makeFileName()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
			ImageIcon tileIcon = new ImageIcon(currentTileImage);

			Main.board[rowToShift][0].setIcon(tileIcon);

			Main.board[rowToShift][0].setRow(rowToShift);
			Main.board[rowToShift][0].setColumn(0);

			System.out.println();
			Main.board[rowToShift][8] = Main.board[rowToShift][7];

			for (int i = 7; i >=  1; i--) {

				Main.board[rowToShift][i] = Main.board[rowToShift ][i - 1];
				Main.board[rowToShift][i].setColumn(i);

				Main.board[rowToShift][i].setLocation(Main.board[rowToShift][i].getX() + 60, Main.board[rowToShift][i].getY());

			}

			Main.extraTile = Main.board[rowToShift][8];

			Main.board[rowToShift][0].setVisible(false);
			Main.board[rowToShift][8].setVisible(false);

			Main.board[rowToShift][1].setVisible(true);

			Main.board[rowToShift][8] = null;
			Main.board[rowToShift][0] = null;
		}
		else if (directionOfShift == LEFT){

			Main.board[rowToShift][8] = Main.extraTile;

			BufferedImage extraTileImg = null;

			try {
				extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.board[rowToShift][8].makeFileName()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
			ImageIcon tileIcon = new ImageIcon(currentTileImage);

			Main.board[rowToShift][8].setIcon(tileIcon);

			Main.board[rowToShift][8].setRow(rowToShift);
			Main.board[rowToShift][8].setColumn(8);


			Main.board[rowToShift][0] = Main.board[rowToShift][1];

			for (int i = 1; i <=  7; i++) {

				Main.board[rowToShift][i] = Main.board[rowToShift][i + 1];
				Main.board[rowToShift][i].setColumn(i);

				Main.board[rowToShift][i].setLocation(Main.board[rowToShift][i].getX() - 60, Main.board[rowToShift][i].getY());

			}

			Main.extraTile = Main.board[rowToShift][0];

			Main.board[rowToShift][0].setVisible(false);
			Main.board[rowToShift][8].setVisible(false);

			Main.board[rowToShift][7].setVisible(true);

			Main.board[rowToShift][8] = null;
			Main.board[rowToShift][0] = null;

		}
		else if (directionOfShift == UP) {

			Main.board[8][columnToShift] = Main.extraTile;

			BufferedImage extraTileImg = null;

			try {
				extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.board[8][columnToShift].makeFileName()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
			ImageIcon tileIcon = new ImageIcon(currentTileImage);

			Main.board[8][columnToShift].setIcon(tileIcon);

			Main.board[8][columnToShift].setRow(8);
			Main.board[8][columnToShift].setColumn(columnToShift);

			Main.board[0][columnToShift] = Main.board[1][columnToShift];

			for (int i = 1; i <= 7; i++) {

				Main.board[i][columnToShift] = Main.board[i + 1][columnToShift];
				Main.board[i][columnToShift].setRow(i);

				Main.board[i][columnToShift].setLocation(Main.board[i][columnToShift].getX(), Main.board[i][columnToShift].getY() - 60);

			}
			Main.extraTile = Main.board[0][columnToShift];

			Main.board[0][columnToShift].setVisible(false);
			Main.board[8][columnToShift].setVisible(false);

			Main.board[7][columnToShift].setVisible(true);

			Main.board[0][columnToShift] = null;
			Main.board[8][columnToShift] = null;
		}
		else if (directionOfShift == DOWN) {

			Main.board[0][columnToShift] = Main.extraTile;

			BufferedImage extraTileImg = null;

			try {
				extraTileImg = ImageIO.read(new File("Labyrinth/res/Images/" + Main.board[0][columnToShift].makeFileName()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Image currentTileImage = extraTileImg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
			ImageIcon tileIcon = new ImageIcon(currentTileImage);

			Main.board[0][columnToShift].setIcon(tileIcon);

			Main.board[0][columnToShift].setRow(0);
			Main.board[0][columnToShift].setColumn(columnToShift);

			Main.board[8][columnToShift] = Main.board[7][columnToShift];


			for (int i = 7; i >= 1; i--) {

				Main.board[i][columnToShift] = Main.board[i - 1][columnToShift];
				Main.board[i][columnToShift].setRow(i);

				Main.board[i][columnToShift].setLocation(Main.board[i][columnToShift].getX(), Main.board[i][columnToShift].getY() + 60);
			}

			Main.extraTile = Main.board[8][columnToShift];

			Main.board[0][columnToShift].setVisible(false);
			Main.board[8][columnToShift].setVisible(false);

			Main.board[1][columnToShift].setVisible(true);

			Main.board[0][columnToShift] = null;
			Main.board[8][columnToShift] = null;
		}

		checkIfPlayerOverflowed();

	}

	public static void checkIfPlayerOverflowed() {


		// Check if a player has been pushed outside the playable board, returns them to the appropriate row and column
		for (int i = 0; i < Main.deck.players.size(); i++) {

			if (Main.deck.players.get(i).getColumns() == 8) {
				Main.deck.players.get(i).setColumns(1);

				Main.board[Main.deck.players.get(i).getRows()][1].add(Main.deck.players.get(i));

				Main.board[Main.deck.players.get(i).getRows()][1].repaint();

			}
			else if (Main.deck.players.get(i).getColumns() == 0) {
				Main.deck.players.get(i).setColumns(7);

				Main.board[Main.deck.players.get(i).getRows()][7].add(Main.deck.players.get(i));

				Main.board[Main.deck.players.get(i).getRows()][7].repaint();
			}
			else if (Main.deck.players.get(i).getRows() == 8) {
				Main.deck.players.get(i).setRows(1);

				Main.board[1][Main.deck.players.get(i).getColumns()].add(Main.deck.players.get(i));

				Main.board[1][Main.deck.players.get(i).getColumns()].repaint();

				Main.board[Main.deck.players.get(i).getRows()][1].repaint();
			}
			else if (Main.deck.players.get(i).getRows() == 0) {
				Main.deck.players.get(i).setRows(7);

				Main.board[7][Main.deck.players.get(i).getColumns()].add(Main.deck.players.get(i));

				Main.board[7][Main.deck.players.get(i).getColumns()].repaint();
			}

		}

	}

	private static void checkTileOccupied(int rowIndex, int columnIndex, int directionOfShift) {

		// Checks if any player is on the tile being moved
		for (int i = 0; i < Main.deck.players.size(); i++) {


			int playerColumn = Main.deck.players.get(i).getColumns();
			int playerRow = Main.deck.players.get(i).getRows();

			if (directionOfShift == RIGHT) {

				if (playerRow == rowIndex) {
					Main.deck.players.get(i).setColumns(playerColumn + 1);
				}
			}
			else if (directionOfShift == LEFT) {

				if (playerRow == rowIndex) {
					Main.deck.players.get(i).setColumns(playerColumn - 1);
				}
			}
			else if (directionOfShift == UP) {

				if (playerColumn == columnIndex) {
					Main.deck.players.get(i).setRows(playerRow - 1);
				}
			}
			else if (directionOfShift == DOWN) {

				if (playerColumn == columnIndex) {
					Main.deck.players.get(i).setRows(playerRow + 1);
				}
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
