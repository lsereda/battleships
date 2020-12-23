package uj.lsereda.battleships.user_command_receiver;

import java.util.Scanner;

public class ScannerCommandReceiver implements CommandReceiver {

    private final Scanner scan;

    public ScannerCommandReceiver(Scanner scan) {
        this.scan = scan;
    }

    @Override
    public String receiveCommand() {
        return scan.next();
    }
}
