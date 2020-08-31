package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.DAOimplementation;
import org.example.DAO.DBConnection;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;


public class Guestbook implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        List<Entry> entries = new ArrayList<Entry>();
        DBConnection connection = new DBConnection();
        DAOimplementation daoImpl = new DAOimplementation(connection);

        List<Entry> allEntries = daoImpl.getAllEntries("SELECT * FROM entry;");
        for (Entry entry : allEntries) {
            entries.add(entry);
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/guestbook.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("entries", entries);

        String response = template.render(model);

        if(method.equals("GET")){
            response =
                    template.render(model);
        }
        if(method.equals("POST")){
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            System.out.println(formData);
            Map inputs = parseFormData(formData);
            Date date = new Date();
            Entry entry = new Entry(inputs.get("note").toString(), inputs.get("name").toString(), date.toString());
            daoImpl.addEntry(entry);
            entries.add(entry);
            response =
                    template.render(model); /*+*/
                            /*"<html><body>" +
                            "<form method=\"POST\">\n" +
                            "  Note:<br>\n" +
                            "  <textarea rows=\"4\" cols=\"50\" type=\"text\" name=\"note\" value=\"\"></textarea>\n" +
                            "  <br>\n" +
                            "  Name:<br>\n" +
                            "  <input type=\"text\" name=\"name\" value=\"\">\n" +
                            "  <br><br>\n" +
                            "  <input type=\"submit\" value=\"Add note\">\n" +
                            "</form> " +
                            "</body></html>";*/
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }

}
