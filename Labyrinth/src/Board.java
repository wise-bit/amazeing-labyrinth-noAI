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

        Setup setupStart = new Setup();
        set = setupStart.getBoard();
        
        
        
        // SHRILL MAKE 
        for (int i = 0; i < playersArray.length; i++) {
        	
        	playersArray[i] = new Player(playerHand, playerName, playerColour, rows, columns)
        }
    	
    }
//
//    add tiles
//    16 stationary tiles
//    randomly assign moveable tiles

    // SHIFT OVER 1 BASED ON DIRECTION OF SHIFT
    // CHECK IF PIECES, TREASURES, OR PLAYERS ARE ON SHIFTED TILES
    // UPDATE POSITIONS OF ALL PIECES MOVED
    // UPDATE THE TILE WHICH THE PLAYER IS NOW ABLE TO MOVE

    public void shiftBoardTiles(int rowToShift, int columnToShift, int directionOfShift){

    	// CHECK IF ANY PEICES THAT ARE BEING MOVES ARE OCCUPIED
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
        		if (checkTileOccupied(set[rowToShift][i])) {
        			
        		}
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

    }
    
    private boolean checkTileOccupied(Tile tileBeingMoved) {

    	
    	if (Player.)
    	return false;
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
