package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class Guestbook implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String response = "";
        String method = httpExchange.getRequestMethod();


        // Send a form if it wasn't submitted yet.
        if(method.equals("GET")){
            response = "<html><body>" +
                    "<form method=\"POST\">\n" +
                    "  Message:<br>\n" +
                    "  <input type=\"text\" name=\"Message\" value=\"\">\n" +
                    "  <br>\n" +
                    "  Name:<br>\n" +
                    "  <input type=\"text\" name=\"Name\" value=\"\">\n" +
                    "  <br><br>\n" +
                    "  <input type=\"submit\" value=\"Submit\">\n" +
                    "</form> " +
                    "</body></html>";
        }

        // If the form was submitted, retrieve it's content.
        if(method.equals("POST")){
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            System.out.println(formData);
            Map inputs = parseFormData(formData);

            response = "<html><body>" +
                    "<h1>Hello " +
                    inputs.get("Message") + "\n " + inputs.get("Name") +
                    "</h1>" +
                    "</body><html>";
        }

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    /**
     * Form data is sent as a urlencoded string. Thus we have to parse this string to get data that we want.
     * See: https://en.wikipedia.org/wiki/POST_(HTTP)
     */
    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;

    }
}