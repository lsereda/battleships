package uj.lsereda.battleships.view;

import java.util.function.Supplier;

public enum ViewType {
    TERMINAL(TerminalView::new),
    EMPTY(EmptyView::new);

    private final Supplier<View> constructor;

    ViewType(Supplier<View> constructor) {
        this.constructor = constructor;
    }

    public Supplier<View> getConstructor() {
        return constructor;
    }
}
