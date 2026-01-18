package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Expense {

    private final String expenseId;
    private final double totalAmount;
    private final User paidBy;
    private final List<Split> splits;
    private final SplitType splitType;

    public Expense(double totalAmount, User paidBy, SplitType splitType) {
        this.expenseId = UUID.randomUUID().toString();
        this.totalAmount = totalAmount;
        this.paidBy = paidBy;
        this.splitType = splitType;
        this.splits = new ArrayList<>();
    }

    public static Expense createEqualExpense(double totalAmount, User paidBy, List<User> participants) {
        Expense expense = new Expense(totalAmount, paidBy, SplitType.EQUAL);

        int totalNoOfUsers = participants.size();
        double amountPerUser = totalAmount/totalNoOfUsers;

        for (User user : participants ) {
            Split split = new Split(user, amountPerUser);
            expense.splits.add(split);
        }
        return expense;

    }

    public static Expense createExactExpense(double totalAmount, User paidBy, Map<User, Double> userAmountMap) {
        Expense expense = new Expense(totalAmount, paidBy, SplitType.EXACT);


        for (User user : userAmountMap.keySet() ) {
            Double amount = userAmountMap.get(user);
            Split split = new Split(user, amount);
            expense.splits.add(split);
        }
        return expense;

    }

    public static Expense createPercentExpense(double totalAmount, User paidBy, Map<User, Double> userPercentMap) {
        Expense expense = new Expense(totalAmount, paidBy, SplitType.PERCENT);

        for (User user : userPercentMap.keySet() ) {
            Double amount = (totalAmount * (userPercentMap.get(user)))/ 100;
            Split split = new Split(user, amount);
            expense.splits.add(split);
        }
        return expense;

    }

    public void addSplit(Split split) {
        splits.add(split);
    }

    public String getExpenseId() {
        return expenseId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public SplitType getSplitType() {
        return splitType;
    }
}



