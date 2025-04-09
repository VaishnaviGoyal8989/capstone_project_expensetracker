package net.java.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.java.expensetracker.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}