package uj.lsereda.battleships.turn;

import uj.lsereda.battleships.Session;
import uj.lsereda.battleships.map.Cell;
import uj.lsereda.battleships.map.CellType;

import java.io.IOException;

public class EnemyTurn implements GameTurn { //TODO implement

    private final Session session;

    public EnemyTurn(Session session) {
        this.session = session;
    }

    @Override
    public void perform() throws IOException {
        var message = session.getReader().readLine();
        var fieldX = message.charAt(0);
        var fieldY = Integer.parseInt(String.valueOf(message.charAt(1)));
        var response = getResponse(fieldX, fieldY);

        var writer = session.getWriter();
        writer.write(response);
        writer.newLine();
        writer.flush();

        System.out.println("My map:");
        System.out.println(session.getMyMap());
        System.out.println("Enemy map:");
        System.out.println(session.getEnemyMap());
    }

    private String getResponse(char x, int y) {
        var parsedX = x - 65;
        var cell = session.getMyMap().getCells()[parsedX][y];
        if (cell.getCellType().equals(CellType.WATER)) {
            return String.format("%c%d MISS", x, y);
        } else {
            var iterator = session.getMyMap().iterator(CellType.SHIP);
            if (!iterator.hasNext()) {
                return String.format("%c%d WIN", x, y); //TODO implement win process
            }
            session.getMyMap().setCell(parsedX, y, new Cell(CellType.HIT, parsedX, y));
            while (iterator.hasNext()) {
                var nextCell = iterator.next().getVal();
                if (nextCell.getCellType().equals(CellType.SHIP) &&
                        Math.abs(nextCell.getX() - cell.getX()) <= 1 &&
                        Math.abs(nextCell.getY() - cell.getY()) <= 1) {
                    return String.format("%c%d HIT", x, y);
                }
            }
            return String.format("%c%d DESTROYED", x, y);
        }
    }
}
