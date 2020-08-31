package org.example;

public class Entry {

    String entry;
    String name;
    String date;

    public Entry(String entry, String name, String date) {
        this.entry = entry;
        this.name = name;
        this.date = date;
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
