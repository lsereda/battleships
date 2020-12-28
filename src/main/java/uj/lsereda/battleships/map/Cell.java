package uj.lsereda.battleships.map;

import java.util.Objects;

public class Cell implements Comparable<Cell> {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y &&
                cellType == cell.cellType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellType, x, y);
    }

    public Cell clone() {
        return new Cell(this);
    }
}
