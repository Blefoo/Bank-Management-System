package net.blefoo.bankmanagement;

import java.util.*;

public class Person extends Bank {

    Random random = new Random();
    private String firstName, middleName, lastName;
    private static Set<Integer> ssnSet = new HashSet<>();
    private final int socialSecurityNumber = generateSSN();
    private int age;
    private int balance = 1250;
    private List<CheckingAccount> accounts = new ArrayList<>();


    public Person(String firstName, String middleName, String lastName, int age) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person() {

    }

    public int generateSSN() {
        int ssn;
        do {
            ssn = random.nextInt(500000);
        } while (ssnSet.contains(ssn));
        ssnSet.add(ssn);
        return ssn;
    }

    public String getFullName() {
        return (firstName + " " + middleName + " " + lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void addAccount(CheckingAccount account) {
        accounts.add(account);
    }

    public List<CheckingAccount> getAccounts() {
        return accounts;
    }

    public void addBalance(int balance) {
        this.balance += balance;
    }

    public void removeBalance(int balance) {
        this.balance -= balance;
    }

    public int getBalance() {
        return balance;
    }

}
