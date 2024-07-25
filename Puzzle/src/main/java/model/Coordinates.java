package model;

public class Coordinates implements Comparable<Coordinates> {
    private int x;
    private int y;
    public Coordinates(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Coordinates o) {
        int d = this.x - o.x;
        if (d == 0) d = this.y - o.y;
        return d;
    }
}
