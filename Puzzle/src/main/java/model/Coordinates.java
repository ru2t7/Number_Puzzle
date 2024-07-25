package model;

public record Coordinates(int x, int y) implements Comparable<Coordinates> {

    @Override
    public int compareTo(Coordinates o) {
        int d = this.x - o.x;
        if (d == 0) d = this.y - o.y;
        return d;
    }
}
