package uj.lsereda.battleships.view;

import java.util.function.Supplier;

public enum ViewType {
    TERMINAL(TerminalView::new),
    EMPTY(EmptyView::new);

    private final Supplier<IView> constructor;

    ViewType(Supplier<IView> constructor) {
        this.constructor = constructor;
    }

    public Supplier<IView> getConstructor() {
        return constructor;
    }
}
