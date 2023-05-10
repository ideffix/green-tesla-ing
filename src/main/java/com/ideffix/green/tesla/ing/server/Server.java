package com.ideffix.green.tesla.ing.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {
    private HttpServer server;
    private final String hostname;
    private final int port;

    private Server(String hostname, int port) throws IOException {
        this.hostname = hostname;
        this.port = port;
        init();
    }

    private void init() throws IOException {
        server = HttpServer.create(new InetSocketAddress(hostname, port), 0);
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        server.setExecutor(Executors.newFixedThreadPool(availableProcessors));
    }

    public static Server from(String hostname, int port) {
        try {
            return new Server(hostname, port);
        } catch (IOException e) {
            // do not propagate error and just fail fast
            System.err.println("Failed to run server, details: " + e.getMessage());
            System.exit(1);
            // returning null so code can compile but this is dead code as System.exit will kill the process
            return null;
        }
    }

    public void start() {
        server.start();

    }

    public <Request, Response> void addController(HttpController<Request, Response> httpController) {
        server.createContext(httpController.getPath(), httpController);
    }
}
