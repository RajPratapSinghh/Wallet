package org.example;
import java.sql.*;
import java.util.*;
public class DBConnection {
    private Connection conn;
    public Connection getConn() {
        return this.conn;
    }

    public DBConnection() {
        // Connect to the MySQL database
        String url = "jdbc:mysql://localhost:3306/searchaccio";
        String username = "root";
        String password = "Princy@2601";
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the MySQL database successfully!");
        } catch (SQLException e) {
            System.out.println("Error connecting to the MySQL database: " + e.getMessage());
        }
    }

    public void insertTransaction(String type, double amount) {
        // Insert a new transaction into the MySQL database
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO transactions (type, amount) VALUES (?, ?)");
            stmt.setString(1, type);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
            System.out.println("Transaction added successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting transaction: " + e.getMessage());
        }
    }

    public List<Transaction> getTransactionHistory() {
        // Retrieve the transaction history from the MySQL database
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transactions");
            while (rs.next()) {
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                transactions.add(new Transaction(type, amount));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving transaction history: " + e.getMessage());
        }
        return transactions;
    }
}
