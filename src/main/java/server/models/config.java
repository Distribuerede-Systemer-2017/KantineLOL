package server.models;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class config {


    private static String serverUrl;

    public static JsonObject initConfig() {

        JsonObject json = new JsonObject();

        try {
            JsonParser parserJ = new JsonParser();
            json = (JsonObject) parserJ.parse(new FileReader("src/Sdk/config.json"));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String getServerUrl() {
        return serverUrl;
    }

    public static void setServerUrl(String serverUrl) {
        config.serverUrl = serverUrl;
    }

}
