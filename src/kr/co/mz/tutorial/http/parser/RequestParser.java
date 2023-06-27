package kr.co.mz.tutorial.http.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestParser {
    private final Socket clientSocket;

    public RequestParser(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public String getRequestLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return reader.readLine();
    }

    public String getUrl(String request) {
        if (request != null) {
            String[] parts = request.split(" ");
            if (parts.length >= 2) {
                if (parts[1].endsWith("?")) {
                    return parts[1].substring(0, parts[1].length() - 1);
                } else {
                    int questionIndex = parts[1].indexOf('?');
                    if (questionIndex != -1) {
                        return parts[1].substring(0, questionIndex);
                    }
                    return parts[1];
                }
            }
        }
        return "";
    }
}
