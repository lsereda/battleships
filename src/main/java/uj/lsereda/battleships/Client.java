package uj.lsereda.battleships;

import uj.lsereda.battleships.map.Map;
import uj.lsereda.battleships.user_command_receiver.ScannerCommandReceiver;
import uj.lsereda.battleships.view.ViewFactory;
import uj.lsereda.battleships.view.ViewType;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

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
            var view = ViewFactory.getView(ViewType.TERMINAL);
            var bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            var receiver = new ScannerCommandReceiver(new Scanner(System.in));
            var myMap = Map.fromFile(mapPath);
            //TODO add rest of components
            var session = new Session.SessionBuilder()
                    .withSocket(socket)
                    .withReader(bufferedReader)
                    .withWriter(bufferedWriter)
                    .withReceiver(receiver)
                    .withMyMap(myMap)
                    .build();

            new Thread(session, "client").start();
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
