package uj.lsereda.battleships.view;

public class ViewFactory {

    public static View getView(ViewType type) {
        return type.getConstructor().get();
    }
}
