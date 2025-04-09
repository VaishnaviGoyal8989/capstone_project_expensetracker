package net.java.expensetracker.controller;

import jakarta.servlet.http.HttpSession;
import net.java.expensetracker.model.Expense;
import net.java.expensetracker.model.ManagerExpense;
import net.java.expensetracker.model.User;
import net.java.expensetracker.model.BudgetDept;
import net.java.expensetracker.service.FinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financeTeam")
@CrossOrigin
public class FinanceController {

    @Autowired
    private FinService finService;

    //  View all employee expenses
    @GetMapping("/employeeExpenses")
    public List<Expense> getAllEmployeeExpenses(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user != null && user.getRole().equalsIgnoreCase("Finance Team")) {
            return finService.getAllEmployeeExpenses();
        }
        return null;
    }

    //  View all manager expenses
    @GetMapping("/managerExpenses")
    public List<ManagerExpense> getAllManagerExpenses(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user != null && user.getRole().equalsIgnoreCase("Finance Team")) {
            return finService.getAllManagerExpenses();
        }
        return null;
    }
    @PostMapping("/updateManagerExpenseStatus/{id}")
    public String updateManagerExpenseStatus(@PathVariable Integer id, @RequestParam String status) {
        finService.updateManagerExpenseStatus(id, status);
        return "Status updated";
    }
    
    @PostMapping("/updateEmployeeExpenseStatus/{id}")
    public String updateEmployeeExpenseStatus(@PathVariable Integer id, @RequestParam String status) {
        finService.updateEmployeeExpenseStatus(id, status);
        return "Employee Status updated";
    }
 //  View all Budgets
    @GetMapping("/budgets")
    public List<BudgetDept> getAllBudgets(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user != null && user.getRole().equalsIgnoreCase("Finance Team")) {
            return finService.getAllBudgets();
        }
        return null;
    }

    // Add Budget
    @PostMapping("/addBudget")  
    public String addBudget(@RequestBody BudgetDept budgetDept, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user != null && user.getRole().equalsIgnoreCase("Finance Team")) {
            finService.addBudget(budgetDept);
            return "Budget Added Successfully";
        }
        return "Unauthorized Access";
    }



}