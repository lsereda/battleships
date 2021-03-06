package uj.lsereda.battleships.maybe_monad;

import java.util.function.Function;

public interface Maybe<T> {

    static <T> Maybe<T> unit(T a) {
        if (a == null) {
            return new Nothing<>();
        } else {
            return new Just<>(a);
        }
    }

    Maybe<T> bind(Function<T, Maybe<T>> f);

    Maybe<T> fmap(Function<T, T> f);

    boolean isJust();

    boolean isNothing();

    T getVal();
}
