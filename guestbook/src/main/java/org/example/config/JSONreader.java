package org.example.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONreader {

    public Map<String, String> JSONread() {
        Map<String, String> connectionData = new HashMap<String, String>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/dbData.JSON"));
            JSONObject jsonObject =  (JSONObject) obj;
            String connection = (String) jsonObject.get("DBConnection");
            connectionData.put("connection", connection);
            String user = (String) jsonObject.get("DBUser");
            connectionData.put("user", user);
            String password = (String) jsonObject.get("DBPassword");
            connectionData.put("password", password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return connectionData;
    }

}
