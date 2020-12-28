package uj.lsereda.battleships;

import uj.lsereda.battleships.map.Map;
import uj.lsereda.battleships.turn.EnemyTurn;
import uj.lsereda.battleships.user_command_receiver.ScannerCommandReceiver;
import uj.lsereda.battleships.view.ViewFactory;
import uj.lsereda.battleships.view.ViewType;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server implements Runnable {
    private static Server instance;
    private final InetAddress address;
    private final int port;
    private final String mapPath;
    private final ServerSocket socket;

    private Server(InetAddress address, int port, String mapPath) throws IOException {
        this.address = address;
        this.port = port;
        this.mapPath = mapPath;
        this.socket = new ServerSocket(port, 2, address);
    }

    public static Server getInstance(InetAddress address, int port, String mapPath) throws IOException {
        if (instance == null) {
            instance = new Server(address, port, mapPath);
        }
        return instance;
    }

    public void start() {
        try {
            new Thread(instance, "Server").start();
            System.out.println("Running server at address: " + address + ", port: " + port);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            var acceptedSocket = socket.accept();
            var view = ViewFactory.getView(ViewType.TERMINAL);
            var bufferedReader = new BufferedReader(new InputStreamReader(acceptedSocket.getInputStream()));
            var bufferedWriter = new BufferedWriter(new OutputStreamWriter(acceptedSocket.getOutputStream()));
            var receiver = new ScannerCommandReceiver(new Scanner(System.in));
            var myMap = Map.fromFile(mapPath);
            var enemyMap = Map.foggedMap();

            var session = new Session.SessionBuilder()
                    .withSocket(acceptedSocket)
                    .withReader(bufferedReader)
                    .withWriter(bufferedWriter)
                    .withReceiver(receiver)
                    .withMyMap(myMap)
                    .withEnemyMap(enemyMap)
                    .build();
            var state = new EnemyTurn(session);
            session.setState(state);

            new Thread(session, "server").start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "Server{" +
                "port=" + port +
                ", mapPath='" + mapPath + '\'' +
                '}';
    }
}
