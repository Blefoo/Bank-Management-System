package net.blefoo.bankmanagement;

import javax.swing.*;

public class BankManagementSystem {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to the \n\tBank Management System!\nWe'll need some information, click \"ok.\"");
        while(true) {
            String firstName = JOptionPane.showInputDialog("What's your first name?");
            String middleName = JOptionPane.showInputDialog("What's your middle name? Enter \"-\" if you don't have one.");
            String lastName = JOptionPane.showInputDialog("What's your last name?");
            int age = Integer.parseInt(JOptionPane.showInputDialog("How old are you?"));
            if (getInformations(firstName, middleName, lastName, age)) {
                Person person = new Person(firstName, middleName, lastName, age);
                Bank.addToList(person.getSocialSecurityNumber(), person);
                JOptionPane.showMessageDialog(null, "Registered successfully!\n" + person.getFullName() +"\nSave your Social Security Number: " + person.getSocialSecurityNumber());
                break;
            }
        }
        boolean checkAccount = false;
        int ssn = -1;
        String fullName = " ";
        do {
            try {
                ssn = Integer.parseInt(JOptionPane.showInputDialog("Enter your SSN (Social Security Number): "));
                fullName = JOptionPane.showInputDialog("What's your full name?");
                if (!fullName.equals(Bank.getPerson(ssn).getFullName())) {
                    JOptionPane.showMessageDialog(null, "That's not your Account!");
                } else {
                    checkAccount = true;
                    JOptionPane.showMessageDialog(null, "Logging in...");
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "That's not your Account!");
            }
        } while (!checkAccount);

        int actionMenu = 0;
        while (actionMenu != 4) {
            try {

                actionMenu = Integer.parseInt(JOptionPane.showInputDialog("Welcome " + fullName + "!\nWhat do you want to do?\n\n1 - Create a Checking Account\n2 - List my Checking Accounts\n3 - Show my balance\n4 - Exit"));
                switch (actionMenu) {
                    case 1:
                        if (Bank.getPerson(ssn).getAccounts().size() == 5) {
                            JOptionPane.showMessageDialog(null, "You have reached your current account limit");
                        } else {
                            String accountName = JOptionPane.showInputDialog("Enter the Checking Account Name:");
                            int currentValue;
                            do {
                                currentValue = Integer.parseInt(JOptionPane.showInputDialog("Enter a initial deposit: (Minimum: 50)"));
                            } while (currentValue > (Bank.getMaximumOverdrawAmount() + Bank.getPerson(ssn).getBalance()) && currentValue < 50);
                            Bank.getPerson(ssn).addAccount(new CheckingAccount(accountName, currentValue, ssn));
                        }
                        break;
                    case 2:
                        int indexCheckingAccount;
                        do {
                            StringBuilder message = new StringBuilder("Here are your accounts, " + Bank.getPerson(ssn).getFirstName() + ":\nEnter a value:\n");
                            for (int i = 0; i < Bank.getPerson(ssn).getAccounts().size(); i++) {
                                CheckingAccount acc = Bank.getPerson(ssn).getAccounts().get(i);
                                message.append(i).append(" - ").append(acc.getAccountName()).append(" $").append(acc.getCurrentValue()).append("\n");
                            }
                            indexCheckingAccount = Integer.parseInt(JOptionPane.showInputDialog(message.toString()));
                        } while (indexCheckingAccount > (Bank.getPerson(ssn).getAccounts().size() - 1) || indexCheckingAccount < 0);
                        int actionAccount = Integer.parseInt(JOptionPane.showInputDialog(Bank.getPerson(ssn).getAccounts().get(indexCheckingAccount).getAccountName() + " - $" + Bank.getPerson(ssn).getAccounts().get(indexCheckingAccount).getCurrentValue() + "\nEnter a action:\n" +
                                "\n1 - Deposit Money" +
                                "\n2 - Take Money"));
                        switch (actionAccount) {
                            case 1:
                                int deposit = Integer.parseInt(JOptionPane.showInputDialog("How much do you want to deposit?"));
                                Bank.getPerson(ssn).getAccounts().get(indexCheckingAccount).depositMoney(ssn, deposit);
                                break;
                            case 2:
                                int take = Integer.parseInt(JOptionPane.showInputDialog("How much do you want to take?"));
                                Bank.getPerson(ssn).getAccounts().get(indexCheckingAccount).takeMoney(ssn, take);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Enter a valid value!");
                                break;
                        }
                        break;
                    case 3:
                         JOptionPane.showMessageDialog(null, fullName + "\nYour balance is: $" + Bank.getPerson(ssn).getBalance());
                         break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Exiting...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Enter a valid value!");
                        break;
                    }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter a valid value!");
            }
        }


    }

    public static boolean getInformations(String firstName, String middleName, String lastName, int age) {
        try {
            if (!firstName.isBlank() && !middleName.isBlank() && !lastName.isBlank() && age >= 18 && age <= 120) {
                return true;
            }
            JOptionPane.showMessageDialog(null, "Enter a valid value!");
            return false;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid value!");
            return false;
        }
    }

}
