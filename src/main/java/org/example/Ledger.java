package org.example;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Ledger {

    private final Map<String, Map<String, Double>> balances = new HashMap<>();


    public void addExpense(Expense expense) {

        String payerId = expense.getPaidBy().getUserId();

        for (Split split : expense.getSplits()) {

            String userId = split.getUser().getUserId();


            if (userId.equals(payerId)) {
                continue;
            }

            double amount = split.getAmount();

            addBalance(userId, payerId, amount);
            addBalance(payerId, userId, -amount);
        }
    }


    public void removeExpense(Expense expense) {

        String payerId = expense.getPaidBy().getUserId();

        for (Split split : expense.getSplits()) {

            String userId = split.getUser().getUserId();

            if (userId.equals(payerId)) {
                continue;
            }

            double amount = split.getAmount();

            addBalance(userId, payerId, -amount);
            addBalance(payerId, userId, amount);
        }
    }


    private void addBalance(String from, String to, double amount) {

        balances.putIfAbsent(from, new HashMap<>());
        Map<String, Double> innerMap = balances.get(from);

        double updated = innerMap.getOrDefault(to, 0.0) + amount;
        innerMap.put(to, updated);

    }


    public void printBalances() {

        if (balances.isEmpty()) {
            System.out.println("No balances");
            return;
        }

        for (String from : balances.keySet()) {
            for (String to : balances.get(from).keySet()) {

                double amount = balances.get(from).get(to);

                if (amount > 0) {
                    System.out.println(from + " owes " + to + " : ₹" + amount);
                }
            }
        }
    }


    public void simplifyDebts() {

        Map<String, Double> netBalance = new HashMap<>();


        for (String from : balances.keySet()) {
            for (String to : balances.get(from).keySet()) {
                double amount = balances.get(from).get(to);
                netBalance.put(from, netBalance.getOrDefault(from, 0.0) - amount);
                netBalance.put(to, netBalance.getOrDefault(to, 0.0) + amount);
            }
        }


        Queue<Map.Entry<String, Double>> debtors = new ArrayDeque<>();
        Queue<Map.Entry<String, Double>> creditors = new ArrayDeque<>();

        for (Map.Entry<String, Double> entry : netBalance.entrySet()) {
            if (entry.getValue() < 0) {
                debtors.add(entry);
            } else if (entry.getValue() > 0) {
                creditors.add(entry);
            }
        }


        System.out.println("Simplified Debts:");

        while (!debtors.isEmpty() && !creditors.isEmpty()) {

            Map.Entry<String, Double> debtor = debtors.poll();
            Map.Entry<String, Double> creditor = creditors.poll();

            double settledAmount = Math.min(-debtor.getValue(), creditor.getValue());

            System.out.println(
                    debtor.getKey() + " owes " +
                            creditor.getKey() + " : ₹" + settledAmount
            );

            double debtorRemaining = debtor.getValue() + settledAmount;
            double creditorRemaining = creditor.getValue() - settledAmount;

            if (debtorRemaining < 0) {
                debtors.add(Map.entry(debtor.getKey(), debtorRemaining));
            }

            if (creditorRemaining > 0) {
                creditors.add(Map.entry(creditor.getKey(), creditorRemaining));
            }
        }
    }



    public Map<String, Map<String, Double>> getBalances() {
        return balances;
    }

}

