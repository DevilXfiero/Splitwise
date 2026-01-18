package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SplitwiseService splitwiseService = new SplitwiseService();


        User userA = new User("A", "Alice");
        User userB = new User("B", "Bob");
        User userC = new User("C", "Charlie");


        splitwiseService.addUser(userA);
        splitwiseService.addUser(userB);
        splitwiseService.addUser(userC);


        List<User> participants = List.of(userA, userB, userC);


        splitwiseService.addEqualExpense(
                300.0,   // total amount
                userA,   // paid by
                participants
        );


        System.out.println("Balances after expense:");
        splitwiseService.showBalances();
    }



}
