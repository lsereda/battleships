package uj.lsereda.battleships.view;

import uj.lsereda.battleships.map.Map;

public interface View {
    public void displayMaps(Map myMap, Map enemyMap);

    public void displayMessage(String message);
}
