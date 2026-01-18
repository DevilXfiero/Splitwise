package Entities;

//public enum  Entities.ExpenseType {
//    EQUAL,
//    PARTITION;
//}


public class Expense {

    private int id;
    private int name;
    private int amount;
    private int total;


    public Expense() {

    }
    public Expense(int id, int name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
