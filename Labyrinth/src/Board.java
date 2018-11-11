import java.io.FileNotFoundException;

/*
 * Author: Satrajit Chatterjee
 * Author: Lazar Glumac
 */


public class Board {

	// DECK IS ARRAY OF STRINGS--  CHECK SLACK
	// CREATE DECK

	private Tile[][] set;
	private int[][] tileset;

	private Player[] playersArray;

	private final int RIGHT = 1;
	private final int LEFT = 2;
	private final int UP = 3;
	private final int DOWN = 4;

	private Tile tileForPlayer;

	public Board() throws FileNotFoundException{

<<<<<<< HEAD
		Setup setupStart = new Setup();
		set = setupStart.getBoard();
=======
    // 	NO NEED FOR LOCAL EXTRA TILE PARAMETER
    public void shiftBoardTiles(int rowToShift, int columnToShift, int directionOfShift){
>>>>>>> refs/remotes/origin/Lazar


<<<<<<< HEAD
=======
            set[rowToShift][0] = getTileForPlayer();
>>>>>>> refs/remotes/origin/Lazar

		// SHRILL MAKE 
		for (int i = 0; i < playersArray.length; i++) {

<<<<<<< HEAD
			playersArray[i] = new Player(playerHand, playerName, playerColour, rows, columns)
		}

	}
=======
                set[rowToShift][i + 1] = set[rowToShift ][i];
            }
            
            setTileForPlayer(set[rowToShift][8]);
            // ADD PLAYER AVAILABLE TILE TO  CLASS
                    // Player.setCurrentTile(set[row_colum_ToShift][8])
            set[rowToShift][8] = null;
            set[rowToShift][0] = null;
        }
        else if (directionOfShift == LEFT){
>>>>>>> refs/remotes/origin/Lazar

<<<<<<< HEAD
	// SHIFT OVER 1 BASED ON DIRECTION OF SHIFT
	// CHECK IF PIECES, TREASURES, OR PLAYERS ARE ON SHIFTED TILES
	// UPDATE POSITIONS OF ALL PIECES MOVED
	// UPDATE THE TILE WHICH THE PLAYER IS NOW ABLE TO MOVE
=======
            set[rowToShift][8] = getTileForPlayer();
>>>>>>> refs/remotes/origin/Lazar

	


	public void shiftBoardTiles(int rowToShift, int columnToShift, int directionOfShift){

		// Checks if any player is on the row/column being shifted (depending on direction of shift)
		checkTileOccupied(rowToShift, columnToShift, directionOfShift);

		if (directionOfShift == RIGHT){

			set[rowToShift][0] = getTileForPlayer();

			for (int i = 1; i <=  7; i++) {

				set[rowToShift][i] = set[rowToShift ][i - 1];
				set[rowToShift][i].setColumn(i);

			}
			
			setTileForPlayer(set[rowToShift][8]);

			set[rowToShift][8] = null;
			set[rowToShift][0] = null;
		}
		else if (directionOfShift == LEFT){

			set[rowToShift][8] = getTileForPlayer();

			for (int i = 7; i >=  1; i--) {

				set[rowToShift][i] = set[rowToShift][i - 1];
				set[rowToShift][i].setColumn(i);

			}

			setTileForPlayer(set[rowToShift][0]);

			set[rowToShift][8] = null;
			set[rowToShift][0] = null;
		}
		else if (directionOfShift == UP) {

			set[8][columnToShift] = getTileForPlayer();

			for (int i = 7; i <= 1; i--) {

				set[i][columnToShift] = set[i - 1][columnToShift];
				set[i][columnToShift].setRow(i);

			}
			setTileForPlayer(set[0][columnToShift]);

			set[0][columnToShift] = null;
			set[8][columnToShift] = null;
		}
		else if (directionOfShift == DOWN) {

			set[0][columnToShift] = getTileForPlayer();

			for (int i = 1; i <= 7; i++) {

				set[i][columnToShift] = set[i - 1][columnToShift];
				set[i][columnToShift].setRow(i);
			}

			setTileForPlayer(set[8][columnToShift]);

			set[0][columnToShift] = null;
			set[8][columnToShift] = null;
		}
		
		checkIfPlayerOverflowed();

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

		for (Tile[] row : set){
			for (Tile column : row){
				// int[][] t = Tile.getShape();
			}
		}

	}

	public void setTileForPlayer(Tile tile) {

		this.tileForPlayer = tile;
	}

	public Tile getTileForPlayer() {

		return this.tileForPlayer;
	}
	public Tile[][] getSet() {
		return set;
	}

	public void setSet(Tile[][] set) {
		this.set = set;
	}
}
