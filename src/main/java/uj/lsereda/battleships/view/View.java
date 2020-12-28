package uj.lsereda.battleships.view;

import uj.lsereda.battleships.map.Map;

public interface View {
    void displayMaps(Map myMap, Map enemyMap);

    void displayMessage(String message);
}
