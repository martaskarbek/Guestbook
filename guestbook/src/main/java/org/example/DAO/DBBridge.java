package org.example.DAO;

public class DBBridge {

    private String DBConnection;
    private String DBUser;
    private String DBPassword;

    public DBBridge(String DBConnection, String DBUser, String DBPassword) {
        this.DBConnection = DBConnection;
        this.DBUser = DBUser;
        this.DBPassword = DBPassword;
    }

    public String getDBConnection() {
        return DBConnection;
    }

    public String getDBName() {
        return DBUser;
    }

    public String getDBPassword() {
        return DBPassword;
    }

}
