package org.example;

import com.sun.net.httpserver.HttpServer;
import org.example.controllers.Guestbook;
import org.example.controllers.Student;

import java.net.InetSocketAddress;
import java.sql.SQLException;

public class App 
{
    public static void main( String[] args ) throws Exception, SQLException
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8058), 0);

        server.createContext("/guestbook", new Guestbook());
        /*
            http://localhost:8058/student/
            http://localhost:8058/student/5
            http://localhost:8058/student/remove/5
            http://localhost:8058/student/update/5
            http://localhost:8058/student/add
         */
        server.createContext("/student", new Student());
        server.createContext("/static", new Static());
        server.setExecutor(null);

        server.start();
        System.out.println("Server has started on port " + server.getAddress().getPort());
    }
}
