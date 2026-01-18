package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitwiseService {

    private final Map<String, User> users;
    private final Ledger ledger;

    public SplitwiseService() {
        this.users = new HashMap<>();
        this.ledger = new Ledger();
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void addEqualExpense(double totalAmount, User paidBy, List<User> participants) {
        Expense expense = Expense.createEqualExpense(totalAmount, paidBy, participants);
        ledger.addExpense(expense);
    }

    public void showBalances() {
        ledger.printBalances();
    }
}

