package uj.lsereda.battleships.map;

import uj.lsereda.battleships.maybe_monad.Maybe;

public interface Iterator<T> {
    boolean hasNext();

    Maybe<T> next();
}
