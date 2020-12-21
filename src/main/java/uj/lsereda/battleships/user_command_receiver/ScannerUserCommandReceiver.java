package uj.lsereda.battleships.user_command_receiver;

import java.util.Scanner;

public class ScannerUserCommandReceiver implements IUserCommandReceiver {

    private final Scanner scan;

    public ScannerUserCommandReceiver(Scanner scan) {
        this.scan = scan;
    }

    @Override
    public String receiveCommand() {
        return scan.next();
    }
}
