package uj.lsereda.battleships.map;

public class Cell implements Comparable<Cell> { //TODO
    private final CellType cellType;
    private final int x;
    private final int y;

    public Cell(CellType cellType, int x, int y) {
        this.cellType = cellType;
        this.x = x;
        this.y = y;
    }

    public Cell(Cell cell) {
        this.cellType = cell.cellType;
        this.x = cell.x;
        this.y = cell.y;
    }

    public CellType getCellType() {
        return cellType;
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

    public Cell clone() {
        return new Cell(this);
    }


}
