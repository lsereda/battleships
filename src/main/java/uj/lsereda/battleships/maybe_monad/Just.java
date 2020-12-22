package uj.lsereda.battleships.maybe_monad;

import java.util.function.Function;

public class Just<T> implements Maybe<T> {
    private final T val;

    public Just(T val) {
        this.val = val;
    }

    @Override
    public Maybe<T> bind(Function<T, Maybe<T>> f) {
        return f.apply(val);
    }

    @Override
    public Maybe<T> fmap(Function<T, T> f) {
        return new Just<>(f.apply(val));
    }

    @Override
    public boolean isJust() {
        return true;
    }

    @Override
    public boolean isNothing() {
        return false;
    }

    public T getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "Just " + val;
    }
}
