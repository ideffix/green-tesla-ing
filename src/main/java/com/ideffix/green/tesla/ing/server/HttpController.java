package com.ideffix.green.tesla.ing.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public abstract class HttpController<Request, Response> implements HttpHandler {
    private final static ObjectMapper mapper = new ObjectMapper();
    private final String path;
    private final TypeReference<Request> typeReference;

    public HttpController(String path, TypeReference<Request> typeReference) {
        this.path = path;
        this.typeReference = typeReference;
    }

    public String getPath() {
        return path;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (!"POST".equals(exchange.getRequestMethod())) {
                throw new RuntimeException("Only POST HTTP methods are supported!");
            }
            Request request = readRequest(exchange);
            Response response = execute(request);
            writeResponse(exchange, response);
        } catch (Exception e) {
            writeErrorResponse(exchange, e);
        }
    }

    private Request readRequest(HttpExchange exchange) throws IOException {
        return mapper.readValue(exchange.getRequestBody(), typeReference);
    }

    private void writeResponse(HttpExchange exchange, Response response) throws IOException {
        String stringResponse = mapper.writeValueAsString(response);
        OutputStream outputStream = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, stringResponse.length());
        outputStream.write(stringResponse.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

    private void writeErrorResponse(HttpExchange exchange, Exception e) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        exchange.sendResponseHeaders(500, e.getMessage().length());
        outputStream.write(e.getMessage().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

    public abstract Response execute(Request request);
}
