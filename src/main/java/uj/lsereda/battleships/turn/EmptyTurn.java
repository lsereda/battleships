package uj.lsereda.battleships.turn;

import uj.lsereda.battleships.Session;

import java.io.IOException;

public class EmptyTurn implements GameTurn { //for testing purposes

    private final Session session;

    public EmptyTurn(Session session) {
        this.session = session;
    }

    @Override
    public void perform() throws IOException {

    }
}
