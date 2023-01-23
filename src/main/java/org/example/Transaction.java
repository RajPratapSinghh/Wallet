package org.example;
public class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String toString() {
        return "Transaction: " + type + ", Amount: " + amount;
    }
}
