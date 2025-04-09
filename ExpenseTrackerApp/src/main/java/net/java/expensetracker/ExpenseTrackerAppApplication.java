package net.java.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerAppApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ExpenseTrackerAppApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace(); 
        }  
    }  
}  