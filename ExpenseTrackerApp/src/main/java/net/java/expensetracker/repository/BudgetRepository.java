package net.java.expensetracker.repository;

import net.java.expensetracker.model.BudgetDept;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BudgetRepository extends JpaRepository<BudgetDept, Integer> {
	BudgetDept findByDepartment(String department);
}
