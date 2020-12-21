package uj.lsereda.battleships.view;

public class ViewFactory {

    public static IView getView(ViewType type) {
        return type.getConstructor().get();
    }
}
