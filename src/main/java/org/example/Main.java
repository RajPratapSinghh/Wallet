package org.example;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.sql.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(WalletTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed!");
        }
        // Create an instance of the Wallet class
        Wallet wallet = new Wallet();
        // Create an instance of the DBConnection class
        DBConnection db = new DBConnection();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("1. Check balance");
            System.out.println("2. Add money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View transaction history");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: " + wallet.checkBalance());
                    break;
                case 2:
                    System.out.print("Enter the amount to add: ");
                    double amount = scanner.nextDouble();
                    wallet.addMoney(amount);
                    // Insert a new transaction into the MySQL database using the DBConnection class
                    db.insertTransaction("add", amount);
                    break;
                case 3:
                    System.out.print("Enter the amount to withdraw: ");
                    amount = scanner.nextDouble();
                    if(wallet.checkBalance()<amount){
                        System.out.println("Insufficient Balance In Account");
                        break;
                    }
                    wallet.withdrawMoney(amount);
                    // Insert a new transaction into the MySQL database using the DBConnection class
                    db.insertTransaction("withdraw", amount);
                    break;
                case 4:
                    // Retrieve the transaction history from the MySQL database using the DBConnection class
                    List<Transaction> transactions = db.getTransactionHistory();
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                    break;
                case 5:
                    // Close the scanner and the MySQL connection1
                    scanner.close();
                    try {
                       db.getConn().close();
                        System.out.println("MySQL connection closed successfully!");
                    } catch (SQLException e) {
                        System.out.println("Error closing MySQL connection: " + e.getMessage());
                    }
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid choice.");
                    break;
            }
        }
    }
}

