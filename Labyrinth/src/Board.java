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
//
//    add tiles
//    16 stationary tiles
//    randomly assign moveable tiles

    // SHIFT OVER 1 BASED ON DIRECTION OF SHIFT
    // CHECK IF PIECES, TREASURES, OR PLAYERS ARE ON SHIFTED TILES
    // UPDATE POSITIONS OF ALL PIECES MOVED
    // UPDATE THE TILE WHICH THE PLAYER IS NOW ABLE TO MOVE

    public void shiftBoardTiles(int row_colum_ToShift, int directionOfShift, Tile extraTile){

    	// CHECK IF ANY PEICES THAT ARE BEING MOVES ARE OCCUPIED
        if (directionOfShift == RIGHT){

            set[row_colum_ToShift][0] = extraTile;

            for (int i = 0; i <=  7; i++) {

                set[row_colum_ToShift][i + 1] = set[row_colum_ToShift ][i];
            }

            // ADD PLAYER AVAILABLE TILE TO PLAYER CLASS
                    // Player.setCurrentTile(set[row_colum_ToShift][8])
            set[row_colum_ToShift][8] = null;
            set[row_colum_ToShift][0] = null;
        }
        else if (directionOfShift == LEFT){

            set[row_colum_ToShift][8] = extraTile;

            for (int i = 7; i >=  1; i++) {

                set[row_colum_ToShift][i] = set[row_colum_ToShift][i - 1];
            }

            // ADD PLAYER AVAILABLE TILE TO PLAYER CLASS
                // Player.setCurrentTile(set[row_colum_ToShift][0])
            set[row_colum_ToShift][8] = null;
            set[row_colum_ToShift][0] = null;
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

    public Tile[][] getSet() {
        return set;
    }

    public void setSet(Tile[][] set) {
        this.set = set;
    }
}
