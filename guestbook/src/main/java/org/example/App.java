package org.example;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8006), 0);

        server.createContext("/home", new Home());
        server.setExecutor(null); // creates a default executor

        server.start();
        System.out.println("Server has started on port " + server.getAddress().getPort());
    }
}
