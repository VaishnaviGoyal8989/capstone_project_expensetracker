package net.java.expensetracker.repository;

import net.java.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByEmployeeEmail(String employeeEmail);
    List<Expense> findByExpenseType(String expenseType);
    List<Expense> findByDepartment(String department);
    }
   
