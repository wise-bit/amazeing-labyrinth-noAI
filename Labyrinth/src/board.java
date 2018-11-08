public class board {

    private tile[][] set;

    public board(){

        set = new tile[9][9];


    }

    public tile[][] getSet() {
        return set;
    }

    public void setSet(tile[][] set) {
        this.set = set;
    }
}
