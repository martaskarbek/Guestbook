package org.example;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class App 
{
    public static void main( String[] args ) throws Exception, SQLException
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8058), 0);

        server.createContext("/guestbook", new Guestbook());
        server.createContext("/static", new Static());
        server.setExecutor(null);

        server.start();
        System.out.println("Server has started on port " + server.getAddress().getPort());
    }
}
