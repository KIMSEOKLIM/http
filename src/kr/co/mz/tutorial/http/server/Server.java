package kr.co.mz.tutorial.http.server;

import kr.co.mz.tutorial.http.parser.RequestParser;
import kr.co.mz.tutorial.http.requestHandler.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //    private final Map<String, String> userCredentials = new HashMap<>();
    //    private final Cache cache = new Cache();
    private final ServerSocket serverSocket;
    private RequestParser requestParser = null;
    private RequestHandler requestHandler = null;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try {
            System.out.println("서버가 시작되었습니다.");
            while (true) {
                // 클라이언트 연결 대기
                Socket clientSocket = serverSocket.accept();
                System.out.println("클라이언트가 연결되었습니다.");
                requestHandler = new RequestHandler(clientSocket);
                requestHandler.handle();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
