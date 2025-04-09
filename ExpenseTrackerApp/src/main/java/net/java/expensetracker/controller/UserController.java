package net.java.expensetracker.controller;

import jakarta.servlet.http.HttpSession;
import net.java.expensetracker.model.User;
import net.java.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Show Registration Page
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // Register User
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        // Call the service to register the user
        String result = userService.register(user);

       
        if (!result.equals("Registration Successful!")) {
            model.addAttribute("error", result);
            return "register";
        }

        
        model.addAttribute("success", result);
        return "login";
    }

    // Show Login Page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Login User
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {

        
        User user = userService.login(email, password);

        
        if (user == null) {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }

        
        session.setAttribute("loggedUser", user);

        // Redirect based on the user's role
        String role = user.getRole();
        if ("Employee".equalsIgnoreCase(role)) {
            return "redirect:/employee/dashboard";
        } else if ("Manager".equalsIgnoreCase(role)) {
            return "redirect:/manager/dashboard";
        } else if ("Finance Team".equalsIgnoreCase(role)) {
            return "redirect:/finance/dashboard";
        } else {
            model.addAttribute("error", "Invalid role assigned!");
            return "login";
        }
    }

    // Employee Dashboard
    @GetMapping("/employee/dashboard")
    public String employeeDashboard() {
        return "employee_dashboard";
    }

    // Manager Dashboard
    @GetMapping("/manager/dashboard")
    public String managerDashboard() {
        return "manager_dashboard";
    }

    // Finance Dashboard
    @GetMapping("/finance/dashboard")
    public String financeDashboard() {
        return "finance_dashboard";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}