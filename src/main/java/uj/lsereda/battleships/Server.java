package uj.lsereda.battleships;

import uj.lsereda.battleships.view.TerminalView;

import java.io.IOException;
import java.net.*;

public class Server implements Runnable { //TODO
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
            var view = new TerminalView();
            //TODO start session
            //TODO run thread
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
