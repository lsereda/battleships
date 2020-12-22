package uj.lsereda.battleships.maybe_monad;

import java.util.function.Function;

public class Nothing<T> implements Maybe<T> {

    public Nothing() {
    }

    @Override
    public Maybe<T> bind(Function<T, Maybe<T>> f) {
        return new Nothing<>();
    }

    @Override
    public Maybe<T> fmap(Function<T, T> f) {
        return new Nothing<>();
    }

    @Override
    public boolean isJust() {
        return false;
    }

    @Override
    public boolean isNothing() {
        return true;
    }

    @Override
    public T getVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Nothing";
    }
}
