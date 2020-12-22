package uj.lsereda.battleships.map;

import uj.lsereda.battleships.maybe_monad.Maybe;

public class MapIterator implements Iterator<Cell> {

    private final Map map;
    private final CellType cellType;
    private int id;

    public MapIterator(Map map, CellType cellType) {
        this.map = map;
        this.cellType = cellType;
        this.id = -1;
    }

    @Override
    public boolean hasNext() {
        for (int i = id + 1; i < 100; i++) {
            if (map.getCells()[i / 10][i % 10].getCellType().equals(cellType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Maybe<Cell> next() {
        for (int i = id + 1; i < 100; i++) {
            var certainCell = map.getCells()[i / 10][i % 10];
            if (certainCell.getCellType().equals(cellType)) {
                id = i;
                return Maybe.unit(certainCell);
            }
        }
        return Maybe.unit(null);
    }
}
