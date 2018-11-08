public class tile {

    private int rotation = 0; // rotation 0,1,2,3
    private int[][] shape; // the layout of each tile
    int[] t;

    public int[][] getShape() {
        return shape;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        if (rotation >= 0 && rotation <= 3)
            this.rotation = rotation;
        else
            this.rotation = 0;
    }

    public int[] getT() {
        return t;
    }

    public void setT(int[] t) {
        this.t = t;
    }

    public tile(int[][] shape){

        setRotation(0);
        setShape(shape);

    }

}
