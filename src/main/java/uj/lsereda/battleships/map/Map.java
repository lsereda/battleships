package uj.lsereda.battleships.map;

import java.util.List;

public class Map { //TODO
    private final List<Cell> cells;
    private final MapIterator iterator;

    public Map(List<Cell> cells, MapIterator iterator) {
        this.cells = cells;
        this.iterator = iterator;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public MapIterator getIterator() {
        return iterator;
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
