package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class WalletTest {
    Wallet wallet = new Wallet();

    @Test
    public void testCheckBalance() {
        wallet.addMoney(100);
        assertEquals(100.0, wallet.checkBalance(), 0);
    }

    @Test
    public void testAddMoney() {
        wallet.addMoney(50);
        assertEquals(50.0, wallet.checkBalance(), 0);
    }

    @Test
    public void testWithdrawMoney() {
        wallet.addMoney(100);
        wallet.withdrawMoney(25);
        assertEquals(75.0, wallet.checkBalance(), 0);
    }
}
