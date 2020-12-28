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
        System.err.println(message);
        var fieldX = message.charAt(0) - 65;
        var fieldY = Integer.parseInt(String.valueOf(message.charAt(1)));
        var response = message.substring(3);

        System.out.println(String.format("%c%d %s", message.charAt(0), fieldY, response));
        if (response.equals("HIT") || response.equals("DESTROYED")) {
            session.getEnemyMap().setCell(fieldX, fieldY, new Cell(CellType.HIT, fieldX, fieldY));
        } else if (response.equals("MISS")) {
            session.getEnemyMap().setCell(fieldX, fieldY, new Cell(CellType.MISS, fieldX, fieldY));
        } else if (response.equals("WIN")) {
            session.setShutdown(true);
            System.out.println("YOU WIN");
        }

        System.out.println("My map:");
        System.out.println(session.getMyMap());
        System.out.println("Enemy map:");
        System.out.println(session.getEnemyMap());
    }
}
