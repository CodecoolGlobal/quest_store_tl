package com.codecool.quest_store.service;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
            String value = URLDecoder.decode(keyValue[1], "UTF-8");
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

    public static Map<String, String> getPOSTInputs(HttpExchange httpExchange) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String data = bufferedReader.readLine();
        return ServiceUtility.parseData(data, ServiceUtility.AND);
    }
}
