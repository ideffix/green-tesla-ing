package com.ideffix.green.tesla.ing;

import com.ideffix.green.tesla.ing.atm.AtmController;
import com.ideffix.green.tesla.ing.onlinegame.OnlineGameController;
import com.ideffix.green.tesla.ing.server.Server;
import com.ideffix.green.tesla.ing.transactions.TransactionController;

public class Main {
    private final static String HOSTNAME = "localhost";
    private final static int PORT = 8080;

    public static void main(String[] args) {
        Server server = Server.from(HOSTNAME, PORT);
        server.addController(new AtmController("/atms/calculateOrder"));
        server.addController(new TransactionController("/transactions/report"));
        server.addController(new OnlineGameController("/onlinegame/calculate"));
        server.start();
    }
}
