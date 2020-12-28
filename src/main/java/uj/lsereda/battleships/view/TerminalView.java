package uj.lsereda.battleships.view;

import uj.lsereda.battleships.map.Map;

public class TerminalView implements View {
    @Override
    public void displayMaps(Map myMap, Map enemyMap) {
        System.out.println("My map: \n" + myMap);
        System.out.println("Enemy map: \n" + enemyMap);
        System.out.println("--------------------------------------------------------");
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
