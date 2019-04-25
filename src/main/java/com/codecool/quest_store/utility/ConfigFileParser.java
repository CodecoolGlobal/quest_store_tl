package com.codecool.quest_store.utility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigFileParser {

    private static final String CONFIG_FILE_PATH = "src/main/resources/config.json";


    public static String getDatabaseURL() throws IOException, ParseException {

        JSONObject jsonObject = getJsonObjectFromConfigFile();

        return ((JSONObject) jsonObject.get("databaseCredentials")).get("url").toString();
}

    public static String getDatabaseUser() throws IOException, ParseException {

        JSONObject jsonObject = getJsonObjectFromConfigFile();

        return ((JSONObject) jsonObject.get("databaseCredentials")).get("user").toString();
}

    public static String getDatabasePassword() throws IOException, ParseException {

        JSONObject jsonObject = getJsonObjectFromConfigFile();

        return ((JSONObject) jsonObject.get("databaseCredentials")).get("password").toString();
}


    private static JSONObject getJsonObjectFromConfigFile() throws IOException, ParseException {
        FileReader fileReader = new FileReader(CONFIG_FILE_PATH);
        JSONParser jsonParser = new JSONParser();

        Object object = jsonParser.parse(fileReader);

        return (JSONObject) object;
    }
}
