package net.java.expensetracker.controller;

import jakarta.servlet.http.HttpSession;
import net.java.expensetracker.model.Expense;
import net.java.expensetracker.model.User;
import net.java.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add-expense")
    public String addExpense(@RequestBody Expense expense, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user !=null) {
        	expense.setDepartment(user.getDepartment());           
        
        return expenseService.addExpense(expense, user.getEmail());
        }
        return "Employee not logged in";
    }

    @GetMapping("/expenses")
    public List<Expense> viewExpenses(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            throw new RuntimeException("Error: User not logged in.");
        }
        return expenseService.viewExpenses(user.getEmail());
    }

    @DeleteMapping("/delete-expense/{id}")
    public String deleteExpense(@PathVariable Integer id, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            return "Error: User not logged in.";
        }
        return expenseService.deleteExpense(id, user.getEmail());
    }
}
