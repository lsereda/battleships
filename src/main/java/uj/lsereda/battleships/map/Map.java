package uj.lsereda.battleships.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Map {
    private final Cell[][] cells;

    public Map(Cell[][] cells) {
        this.cells = cells;
    }

    public static Map fromFile(String mapPath) throws IOException {
        var path = Paths.get(mapPath);
        var content = Files.readAllLines(path);
        var cells = new Cell[10][];
        for (int i = 0; i < 10; i++) {
            cells[i] = new Cell[10];
            for (int j = 0; j < 10; j++) {
                var type = CellType.fromChar(content.get(i).charAt(j));
                cells[i][j] = new Cell(type, i, j);
            }
        }
        return new Map(cells);
    }

    public static Map foggedMap() {
        var cells = new Cell[10][];
        for (int i = 0; i < 10; i++) {
            cells[i] = new Cell[10];
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell(CellType.FOG, i, j);
            }
        }
        return new Map(cells);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public MapIterator iterator(CellType cellType) {
        return new MapIterator(this, cellType);
    }

    public void setCell(int x, int y, Cell cell) {
        cells[x][y] = cell;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                builder.append(cells[i][j].getCellType()).append(' ');
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
