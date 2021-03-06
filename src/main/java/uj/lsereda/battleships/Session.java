package uj.lsereda.battleships;

import uj.lsereda.battleships.map.Map;
import uj.lsereda.battleships.turn.EnemyTurn;
import uj.lsereda.battleships.turn.GameTurn;
import uj.lsereda.battleships.turn.MyTurn;
import uj.lsereda.battleships.turn.WaitingForResponse;
import uj.lsereda.battleships.user_command_receiver.CommandReceiver;
import uj.lsereda.battleships.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Session implements Runnable {
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final Map myMap;
    private final Map enemyMap;
    private final CommandReceiver receiver;
    private final View view;
    private GameTurn state;
    private boolean shutdown = false;

    private Session(SessionBuilder builder) {
        this.reader = builder.reader;
        this.writer = builder.writer;
        this.myMap = builder.myMap;
        this.enemyMap = builder.enemyMap;
        this.receiver = builder.receiver;
        this.view = builder.view;
    }

    @Override
    public void run() {
        while (!shutdown) {
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

    public View getView() {
        return view;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public static class SessionBuilder {
        private BufferedReader reader;
        private BufferedWriter writer;
        private Map myMap;
        private Map enemyMap;
        private CommandReceiver receiver;
        private View view;

        public SessionBuilder() {
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

        public SessionBuilder withView(View view) {
            this.view = view;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }
}
