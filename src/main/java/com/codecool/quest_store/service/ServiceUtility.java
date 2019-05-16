package com.codecool.quest_store.service;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ServiceUtility {

    public static final String AND = "&";
    public static final String SEMICOLON = "; ";

    public static  Map<String, String> parseData(String formData, String parsingSign) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split(parsingSign);
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }

    public static void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static void redirectToContext(HttpExchange httpExchange, String response, String contextPath) throws IOException{
        httpExchange.getResponseHeaders().set("Location", contextPath);

        httpExchange.sendResponseHeaders(302, response.getBytes().length);

        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes(Charset.forName("UTF-8")));
        outputStream.close();
    }
}
