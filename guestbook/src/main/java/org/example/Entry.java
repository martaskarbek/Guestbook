package org.example;

public class Entry {

    int id;
    String entry;
    String name;
    String date;

    public Entry(int id, String entry, String name, String date) {
        this.id = id;
        this.entry = entry;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getEntry() {
        return entry;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

}
