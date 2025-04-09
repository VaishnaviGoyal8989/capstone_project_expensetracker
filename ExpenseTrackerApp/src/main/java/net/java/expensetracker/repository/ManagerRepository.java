package net.java.expensetracker.repository;

import net.java.expensetracker.model.ManagerExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ManagerRepository extends JpaRepository<ManagerExpense, Integer> {
    List<ManagerExpense> findByDepartment(String department);
    List<ManagerExpense> findByManagerEmail(String managerEmail);
    List<ManagerExpense> findByExpenseType(String expenseType);
}

