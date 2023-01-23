package org.example;

public class Wallet {
    private double balance;

    public Wallet() {
        balance = 0.0;
    }

    public double checkBalance() {
        return balance;
    }

    public void addMoney(double amount) {
        balance += amount;
    }

    public void withdrawMoney(double amount) {
        balance -= amount;
    }
}
