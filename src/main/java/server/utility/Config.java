package server.utility;

import com.auth0.jwt.JWT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.io.FileReader;

public class Config {


    private static String DATABASE_HOST;
    private static String DATABASE_PORT;
    private static String DATABASE_NAME;
    private static String DATABASE_USER;
    private static String DATABASE_PASSWORD;

    private static String JWT_SECRET;


    public Config () throws IOException {
        JsonObject jsonConfig = new JsonObject();

        InputStream input = this.getClass().getClassLoader().getResourceAsStream("/config.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        StringBuffer stringBuffer = new StringBuffer();
        String str = "";

        while((str = reader.readLine()) != null){
            stringBuffer.append(str);
        }
        JsonParser parser = new JsonParser();

        jsonConfig = (JsonObject) parser.parse(stringBuffer.toString());

        DATABASE_HOST = jsonConfig.get("DATABASE_HOST").getAsString();
        DATABASE_PORT = jsonConfig.get("DATABASE_PORT").getAsString();
        DATABASE_NAME = jsonConfig.get("DATABASE_NAME").getAsString();
        DATABASE_USER = jsonConfig.get("DATABASE_USER").getAsString();
        DATABASE_PASSWORD = jsonConfig.get("DATABASE_PASSWORD").getAsString();

        JWT_SECRET = jsonConfig.get("JWT_SECRET").getAsString();

        reader.close();
        input.close();
    }

    public static String getDatabaseHost() {
        return DATABASE_HOST;
    }

    public static void setDatabaseHost(String databaseHost) {
        DATABASE_HOST = databaseHost;
    }

    public static String getDatabasePort() {
        return DATABASE_PORT;
    }

    public static void setDatabasePort(String databasePort) {
        DATABASE_PORT = databasePort;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static void setDatabaseName(String databaseName) {
        DATABASE_NAME = databaseName;
    }

    public static String getDatabaseUser() {
        return DATABASE_USER;
    }

    public static void setDatabaseUser(String databaseUser) {
        DATABASE_USER = databaseUser;
    }

    public static String getDatabasePassword() {
        return DATABASE_PASSWORD;
    }

    public static void setDatabasePassword(String databasePassword) {
        DATABASE_PASSWORD = databasePassword;
    }

    public static String getJwtSecret() {
        return JWT_SECRET;
    }

    public static void setJwtSecret(String jwtSecret) {
        JWT_SECRET = jwtSecret;
    }

    /*private static String serverUrl;
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
        Config.serverUrl = serverUrl;
    }*/

}
