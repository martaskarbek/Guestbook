package org.example.DAO;

import org.example.Entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOimplementation implements DAO{

    DBConnection dbConnection;

    public DAOimplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public ResultSet getDataSet(String query) {
        dbConnection.connection();
        try {
            dbConnection.statement = dbConnection.connection.createStatement();
            ResultSet results = dbConnection.statement.executeQuery(query);
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public List<Entry> getAllEntries(String query) {
        List<Entry> allEntries = new ArrayList<>();
        try {
            ResultSet dataSet = getDataSet(query);
            System.out.println("Got record succesfully.\n");
            while (dataSet.next()) {
                final String oneEntry = dataSet.getString("entry");
                final String name = dataSet.getString("name");
                final String date = dataSet.getString("date");
                Entry entry = new Entry(oneEntry, name, date);
                allEntries.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allEntries;
    }

    @Override
    public void addEntry(Entry entry) {
        dbConnection.executeQuery(String.format("INSERT INTO entry(entry, name, date) VALUES ('%s', '%s', '%s');", entry.getEntry(), entry.getName(), entry.getDate()));
        System.out.println("Adder record successfully.");
    }

}
