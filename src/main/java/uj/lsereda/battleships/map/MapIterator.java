package uj.lsereda.battleships.map;

import uj.lsereda.battleships.maybe_monad.Maybe;

public class MapIterator implements Iterator<Cell> {

    private final Map map;
    private final CellType cellType;


    public MapIterator(Map map, CellType cellType) {
        this.map = map;
        this.cellType = cellType;
    }

    @Override
    public boolean hasNext() { //TODO implement
        return false;
    }

    @Override
    public Maybe<Cell> next() { //TODO implement
        return null;
    }
}
