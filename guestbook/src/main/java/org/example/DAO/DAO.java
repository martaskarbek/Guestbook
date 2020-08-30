package org.example.DAO;

import org.example.Entry;

import java.sql.ResultSet;
import java.util.List;

public interface DAO {

    ResultSet getDataSet(String query);
    List<Entry> getAllEntries(String query);
    void addEntry(Entry entry);

}
