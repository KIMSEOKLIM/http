package kr.co.mz.tutorial.http.server;

public class ServerMain {
    private static final int PORT = 8091;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
        server.close();
    }
}
