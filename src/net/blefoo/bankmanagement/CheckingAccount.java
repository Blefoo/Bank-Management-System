package net.blefoo.bankmanagement;

import javax.swing.*;

public class CheckingAccount extends Person {

    private String accountName;
    private int currentValue = 0;

    public CheckingAccount(String accountName, int currentValue, int socialSecurityNumber) {
        this.accountName = accountName;
        this.currentValue += currentValue;
        Bank.getPerson(socialSecurityNumber).removeBalance(currentValue);
        JOptionPane.showMessageDialog(null, "Checking Account created successfully!");
    }

    public boolean depositMoney(int socialSecurityNumber, int deposit) {
        if (deposit <= (getPerson(socialSecurityNumber).getBalance() + getMaximumOverdrawAmount())) {
            currentValue += deposit;
            getPerson(socialSecurityNumber).removeBalance(deposit);
            JOptionPane.showMessageDialog(null, "Deposit made successfully");
            return true;
        }
        return false;
    }

    public boolean takeMoney(int socialSecurityNumber, int takeMoney) {
        if (takeMoney <= currentValue) {
            currentValue -= takeMoney;
            getPerson(socialSecurityNumber).addBalance(takeMoney);
            JOptionPane.showMessageDialog(null, "Withdrawal successful!");
            return true;
        }
        return false;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public String getAccountName() {
        return accountName;
    }

}
