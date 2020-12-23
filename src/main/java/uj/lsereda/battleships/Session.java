package uj.lsereda.battleships;

import uj.lsereda.battleships.map.Map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class Session implements Runnable { //TODO finish
    private final Socket socket;
    private GameState state;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final Map myMap;
    private final Map enemyMap;

    private Session(SessionBuilder builder) {
        this.socket = builder.socket;
        this.state = builder.state;
        this.reader = builder.reader;
        this.writer = builder.writer;
        this.myMap = builder.myMap;
        this.enemyMap = builder.enemyMap;
    }

    @Override
    public void run() { //TODO
        while(true) {

        }
    }

    private static class SessionBuilder {
        private Socket socket;
        private GameState state;
        private BufferedReader reader;
        private BufferedWriter writer;
        private Map myMap;
        private Map enemyMap;

        public SessionBuilder() {
        }

        public SessionBuilder withSocket(Socket socket) {
            this.socket = socket;
            return this;
        }

        public SessionBuilder withState(GameState state) {
            this.state = state;
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

        public Session build() {
            return new Session(this);
        }

    }
}