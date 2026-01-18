package org.example;

public class Split {
    private User user;
    private Double amount;

    public Split(User user, Double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public Double getAmount() {
        return amount;
    }


}
