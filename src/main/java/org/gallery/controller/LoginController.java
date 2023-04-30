package org.gallery.controller;

import org.gallery.model.Account;
import org.gallery.model.repository.AccountRepository;
import org.gallery.view.LoginView;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class LoginController implements Observer {
    final private AccountRepository repository;
    final private LoginView view;

    public LoginController() {
        this.repository = new AccountRepository();
        this.view = new LoginView();

        // Add event listeners to the buttons
        this.view.getLoginButton().addActionListener(e -> handleLogin());
        this.view.getVisitButton().addActionListener(e -> handleVisit());
    }

    public void handleLogin() {
        String username = this.view.getUsernameField().getText();
        String password = this.view.getPasswordField().getText();

        Account account = repository.findByUsername(username);

        if (account == null || !account.getPassword().equals(password)) {

            this.showError("Invalid username or password");
            return;
        }

        if (account.getRole().equals("Employee")) {
            // Forward the user to the employee panel

            // Close the login window
            this.view.dispose();
            // Create the employee controller
            EmployeeController employeeWindow = new EmployeeController();
        } else if (account.getRole().equals("Administrator")) {
            // Forward the user to the admin panel

            // Close the login window
            this.view.dispose();
            // Create the admin controller
            AdminController adminWindow = new AdminController();
        } else {
            this.showError("Something went wrong, please try again later");
        }
    }

    public void handleVisit() {
        // Close the login window
        this.view.dispose();
        // Create the visitor controller
        VisitorController visitorWindow = new VisitorController();
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(view, errorMessage, "", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
