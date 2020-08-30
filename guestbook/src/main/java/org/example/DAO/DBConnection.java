package org.example.DAO;

import org.example.config.JSONreader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    protected Connection connection;
    protected Statement statement;
    JSONreader reader = new JSONreader();
    DBBridge dbBridge = new DBBridge(reader.JSONread().get("connection"), reader.JSONread().get("user"), reader.JSONread().get("password"));

    public void connection() {
        connection = null;
        statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbBridge.getDBConnection(), dbBridge.getDBName(), dbBridge.getDBPassword()/*connectionBridge.getDBconnection()*/);
            System.out.println("Opened database successfully");

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public void executeQuery(String  query){
        connection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch ( Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

}
