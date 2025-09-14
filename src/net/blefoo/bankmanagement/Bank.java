package net.blefoo.bankmanagement;

import java.util.HashMap;

public class Bank {

    private static HashMap<Integer, Person> listOfCustomers = new HashMap();

    private static int maximumOverdrawAmount = 1500;

    public static void addToList(int socialSecurityNumber, Person person) {
        listOfCustomers.put(socialSecurityNumber, person);
    }

    public static Person getPerson(int socialSecurityNumber) {
        return listOfCustomers.get(socialSecurityNumber);
    }

    public static int getMaximumOverdrawAmount() {
        return maximumOverdrawAmount;
    }


}
