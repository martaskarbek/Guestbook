package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.DAOimplementation;
import org.example.DAO.DBConnection;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class Home implements HttpHandler {

    private List<Entry> entries = new ArrayList<Entry>();

    public Home() {
        DBConnection connection = new DBConnection();
        DAOimplementation daoImpl = new DAOimplementation(connection);
        List<Entry> allEntries = daoImpl.getAllEntries("SELECT * FROM entry;");
        for (Entry entry : allEntries) {
            entries.add(entry);
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/guestbook.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("entries", entries);


        String response = template.render(model);

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
