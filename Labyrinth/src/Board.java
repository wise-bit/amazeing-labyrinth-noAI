/*
 * Author: Satrajit Chatterjee
 */

public class Board {

    private Tile[][] set;
    private int[][] tileset;

    public Board(){

        // pls work pls work
        // testing
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
