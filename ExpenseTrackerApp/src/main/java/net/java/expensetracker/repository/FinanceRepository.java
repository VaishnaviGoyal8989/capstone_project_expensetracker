package net.java.expensetracker.repository;

import net.java.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FinanceRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByDepartment(String department);
    List<Expense> findByStatus(String status);
    List<Expense> findByExpenseType(String expenseType);
}
