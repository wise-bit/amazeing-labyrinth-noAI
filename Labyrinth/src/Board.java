/*
 * Author: Satrajit Chatterjee
 * Author: Lazar Glumac
 */

import java.util.logging.Level;

public class Board {

    private Tile[][] set;
    private int[][] tileset;

    private final int RIGHT = 1;
    private final int LEFT = 2;
    private final int UP = 3;
    private final int DOWN = 4;

    private Tile tileForPlayer;
    
    public Board(){

        set = new Tile[9][9];
        
        for (int i = 0; i < 9; i++){
        	
            for (int j = 0; j < 9; j++) {
            	
                // set[i][j] = new Tile;
            }
        }
        tileset = new int[27][27];
        setTileset(tileset);

    }

    // SHIFT OVER 1 BASED ON DIRECTION OF SHIFT
    // CHECK IF PIECES, TREASURES, OR PLAYERS ARE ON SHIFTED TILES
    // UPDATE POSITIONS OF ALL PIECES MOVED
    // UPDATE THE TILE WHICH THE PLAYER IS NOW ABLE TO MOVE

    // 	NO NEED FOR LOCAL EXTRA TILE PARAMETER
    public void shiftBoardTiles(int rowToShift, int columnToShift, int directionOfShift, Tile extraTile){

    	// CHECK IF ANY PEICES THAT ARE BEING MOVES ARE OCCUPIED
        if (directionOfShift == RIGHT){

            set[rowToShift][0] = extraTile;

            for (int i = 0; i <=  7; i++) {

                set[rowToShift][i + 1] = set[rowToShift ][i];
            }

            // CREATE GLOBAL TILE, CHANGE TO EXCESS TILE
            
            setTileForPlayer(set[rowToShift][8]);
            // ADD PLAYER AVAILABLE TILE TO  CLASS
                    // Player.setCurrentTile(set[row_colum_ToShift][8])
            set[rowToShift][8] = null;
            set[rowToShift][0] = null;
        }
        else if (directionOfShift == LEFT){

            set[rowToShift][8] = extraTile;

            for (int i = 7; i >=  1; i--) {

                set[rowToShift][i] = set[rowToShift][i - 1];
            }
            
            setTileForPlayer(set[rowToShift][0]);
            
            set[rowToShift][8] = null;
            set[rowToShift][0] = null;
        }
        else if (directionOfShift == UP) {
        	
        	set[8][columnToShift] = extraTile;
        	
        	for (int i = 7; i <= 1; i--) {
        		
        		set[i][columnToShift] = set[i - 1][columnToShift];
        	}
        	setTileForPlayer(set[0][columnToShift]);
        	
        	// REMOVE THE TILES THAT ARE NOT ACTUALLY PART OF THE BOARD
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
