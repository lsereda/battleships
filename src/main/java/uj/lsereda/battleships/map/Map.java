package uj.lsereda.battleships.map;

import java.util.List;

public class Map { //TODO
    private final List<Cell> cells;

    public Map(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        var sortedCells = (Cell[]) cells.stream().sorted().toArray();
        var builder = new StringBuilder();
        for (int i = 0; i < sortedCells.length; i++) {
            builder.append(sortedCells[i]);
            if (i % 10 == 0 && i > 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
