package uj.lsereda.battleships;

import uj.lsereda.battleships.map.Map;
import uj.lsereda.battleships.turn.EnemyTurn;
import uj.lsereda.battleships.turn.GameTurn;
import uj.lsereda.battleships.turn.MyTurn;
import uj.lsereda.battleships.turn.WaitingForResponse;
import uj.lsereda.battleships.user_command_receiver.CommandReceiver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class Session implements Runnable { //TODO finish
    private final Socket socket;
    private GameTurn state;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final Map myMap;
    private final Map enemyMap;
    private final CommandReceiver receiver;

    private Session(SessionBuilder builder) {
        this.socket = builder.socket;
        this.reader = builder.reader;
        this.writer = builder.writer;
        this.myMap = builder.myMap;
        this.enemyMap = builder.enemyMap;
        this.receiver = builder.receiver;
    }

    @Override
    public void run() { //TODO implement
        while (true) {
            try {
                state.perform();
                if (state instanceof MyTurn) {
                    state = new WaitingForResponse(this);
                } else if (state instanceof WaitingForResponse) {
                    state = new EnemyTurn(this);
                } else if (state instanceof EnemyTurn) {
                    state = new MyTurn(this);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setState(GameTurn state) {
        this.state = state;
    }

    public Socket getSocket() {
        return socket;
    }

    public GameTurn getState() {
        return state;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public Map getMyMap() {
        return myMap;
    }

    public Map getEnemyMap() {
        return enemyMap;
    }

    public CommandReceiver getReceiver() {
        return receiver;
    }

    public static class SessionBuilder {
        private Socket socket;
        private BufferedReader reader;
        private BufferedWriter writer;
        private Map myMap;
        private Map enemyMap;
        private CommandReceiver receiver;

        public SessionBuilder() {
        }

        public SessionBuilder withSocket(Socket socket) {
            this.socket = socket;
            return this;
        }

        public SessionBuilder withReader(BufferedReader reader) {
            this.reader = reader;
            return this;
        }

        public SessionBuilder withWriter(BufferedWriter writer) {
            this.writer = writer;
            return this;
        }

        public SessionBuilder withMyMap(Map myMap) {
            this.myMap = myMap;
            return this;
        }

        public SessionBuilder withEnemyMap(Map enemyMap) {
            this.enemyMap = enemyMap;
            return this;
        }

        public SessionBuilder withReceiver(CommandReceiver receiver) {
            this.receiver = receiver;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }
}
