package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Student implements HttpHandler {
    private JtwigTemplate template;
    private final JtwigModel model = JtwigModel.newModel();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String url = exchange.getRequestURI().getRawPath();
            String[] actions = url.split("/");
            String action = actions.length == 2 ? "" : actions[2].matches("\\d+") ? "details" : actions[2];

            switch (action) {
                case "details":
                    System.out.println("details");
                    template = JtwigTemplate.classpathTemplate("templates/student/details.twig");
                    int id = Integer.parseInt(actions[2]);

                    // get user with given id from db, and return User object
                    model.with("id", id);

                    break;
                case "add":
                    break;
                case "remove":
                    break;
                case "update":
                    System.out.println("update");
                    break;
                default:
                    template = JtwigTemplate.classpathTemplate("templates/student/list.twig");
                    System.out.println("list");
                    List<User> users = getAllStudents();
                    model.with("users", users);
            }

            String response = template.render(model);
            sendOutput(exchange, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<User> getAllStudents() {
        List<User> users = new ArrayList<>();
        User user1 = new User(1, "Adrian", "Widlak", 27);
        User user2 = new User(2, "Jan", "Kowalski", 30);
        User user3 = new User(3, "Jadwiga", "Kowalska", 30);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }

    private void sendOutput(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
