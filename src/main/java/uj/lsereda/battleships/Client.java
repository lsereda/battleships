package uj.lsereda.battleships;

import java.io.IOException;
import java.net.Socket;

public class Client { //TODO

    private static Client instance;
    private final int port;
    private final String mapPath;
    private final String host;

    private Client(int port, String mapPath, String host) {
        this.port = port;
        this.mapPath = mapPath;
        this.host = host;
    }

    public static Client getInstance(int port, String mapPath, String host) {
        if (instance == null) {
            instance = new Client(port, mapPath, host);
        }
        return instance;
    }

    public void start() {
        try {
            var socket = new Socket(host, port);
            //TODO start session
            //TODO run thread
            System.out.println("Client started");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "port=" + port +
                ", mapPath='" + mapPath + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
