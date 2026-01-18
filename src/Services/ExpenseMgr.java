package Services;

import Entities.Expense;
import Entities.ExpenseType;
import Entities.User;

public class ExpenseMgr {
    // add expense (user1, user2, 100)
    // add_expense(user2, user1, 200)
    // getnetExpense(user1.id)
    // user.expense(expense);


    public void addExpense(User from, User to, Expense expense) {

        if(expense.expenseType == ExpenseType.EQUAL) {
            int amount = expense.getAmount();
            int equal_amount = amount / 2;
            Expense fromExpense = new Expense(expense.getId(), expense.getName(), equal_amount);
            from.expenseList.add(fromExpense);

            Expense toExpense = new Expense(expense.getId(), expense.getName(), -equal_amount);
            to.expenseList.add(toExpense);
        }

    }




}
