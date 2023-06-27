package kr.co.mz.tutorial.http.requestHandler;

import kr.co.mz.tutorial.http.cache.Cache;
import kr.co.mz.tutorial.http.parser.QueryParam;
import kr.co.mz.tutorial.http.parser.RequestParser;
import kr.co.mz.tutorial.http.response.ResponseCreator;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    private final Map<String, String> userCredentials = new HashMap<>();
    private final Cache cache = new Cache();
    private Socket clientSocket;
    private final RequestParser requestParser = new RequestParser(clientSocket);

    public RequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void handle() {
        String request = null;
        try {
            request = requestParser.getRequestLine();

            var url = requestParser.getUrl(request);

            String cachedResponse = cache.get(url);
            if (cachedResponse.isEmpty()) {
                ResponseCreator responseCreator = new ResponseCreator(requestParser.getUrl(request), new QueryParam(request), userCredentials);
                cachedResponse = responseCreator.create();
                cache.put(url, cachedResponse);
            }

            clientSocket.getOutputStream().write(cachedResponse.getBytes(StandardCharsets.UTF_8)); //sendResponse
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
