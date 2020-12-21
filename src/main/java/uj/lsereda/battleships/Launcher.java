package uj.lsereda.battleships;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.net.*;

public class Launcher {

    public static void main(String[] args) {
        var options = ourOptions();
        var parser = new DefaultParser();
        try {
            var cmd = parser.parse(options, args);
            var mode = cmd.getOptionValue("mode");
            var port = Integer.parseInt(cmd.getOptionValue("port"));
            var mapPath = cmd.getOptionValue("mapPath");
            if (mode.equals("client")) {
                var host = cmd.getOptionValue("host");
                var client = Client.getInstance(port, mapPath, host);
                client.start();
                System.out.println(client);
            } else {
                var server = Server.getInstance(findAddress(), port, mapPath);
                server.start();
                System.out.println(server);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Options ourOptions() {
        var result = new Options();
        result.addOption("mode", "mode", true, "Player mode");
        result.addOption("port", "port", true, "Port number");
        result.addOption("mapPath", "mapPath", true, "Map path");
        result.addOption("host", "host", true, "Host address");
        return result;
    }

    private static InetAddress findAddress() throws SocketException, UnknownHostException {
        var wlp3s0 = NetworkInterface.getByName("wlp3s0");
        return wlp3s0.inetAddresses()
                .filter(a -> a instanceof Inet4Address)
                .findFirst()
                .orElse(InetAddress.getLocalHost());
    }

}
