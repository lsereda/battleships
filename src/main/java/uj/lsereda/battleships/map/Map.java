package uj.lsereda.battleships.map;

public class Map {
    private final Cell[][] cells;

    public Map(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public MapIterator iterator(CellType cellType) {
        return new MapIterator(this, cellType);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                builder.append(cells[i][j]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
