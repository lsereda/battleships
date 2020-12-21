package uj.lsereda.battleships.map;

public class Cell implements Comparable<Cell> { //TODO
    private final Value value;
    private final int x;
    private final int y;

    public Cell(Value value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public Value getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Cell o) {
        if (this.x < o.x) {
          return -1;
        } else if (this.x == o.x) {
            return Integer.compare(this.y, o.y);
        } else {
            return 1;
        }
    }
}
