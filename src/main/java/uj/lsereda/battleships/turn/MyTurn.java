package uj.lsereda.battleships.turn;

import uj.lsereda.battleships.Session;

import java.io.IOException;

public class MyTurn implements GameTurn {

    private final Session session;

    public MyTurn(Session session) {
        this.session = session;
    }

    @Override
    public void perform() throws IOException {
        var userInput = session.getReceiver().receiveCommand();
        var writer = session.getWriter();
        writer.write(userInput);
        writer.newLine();
        writer.flush();
    }
}
