package uj.lsereda.battleships.turn;

import uj.lsereda.battleships.Session;
import uj.lsereda.battleships.map.Cell;
import uj.lsereda.battleships.map.CellType;

import java.io.IOException;

public class WaitingForResponse implements GameTurn {

    private final Session session;

    public WaitingForResponse(Session session) {
        this.session = session;
    }

    @Override
    public void perform() throws IOException {
        var message = session.getReader().readLine();
        var fieldX = message.charAt(0) - 65;
        var fieldY = Integer.parseInt(String.valueOf(message.charAt(1)));
        var response = message.substring(3);

        var view = session.getView();
        view.displayMessage(String.format("%c%d %s", message.charAt(0), fieldY, response));
        switch (response) {
            case "HIT":
            case "DESTROYED":
                session.getEnemyMap().setCell(fieldX, fieldY, new Cell(CellType.HIT, fieldX, fieldY));
                break;
            case "MISS":
                session.getEnemyMap().setCell(fieldX, fieldY, new Cell(CellType.MISS, fieldX, fieldY));
                break;
            case "WIN":
                session.setShutdown(true);
                view.displayMessage("YOU WIN!!!");
                break;
        }

        view.displayMaps(session.getMyMap(), session.getEnemyMap());
    }
}
