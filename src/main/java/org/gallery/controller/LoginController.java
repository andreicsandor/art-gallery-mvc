package org.gallery.controller;

import org.gallery.model.Account;
import org.gallery.model.Language;
import org.gallery.model.repository.AccountRepository;
import org.gallery.view.LoginView;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class LoginController implements Observer {
    final private AccountRepository repository;
    final private LoginView view;
    final private Language language;

    public LoginController() {
        this.repository = new AccountRepository();
        this.view = new LoginView();
        this.language = new Language();
        language.addObserver(this);

        // Add event listeners to the language menu items
        this.view.getEnMenuItem().addActionListener(e -> handleLanguageSelection("en"));
        this.view.getFrMenuItem().addActionListener(e -> handleLanguageSelection("fr"));
        this.view.getEsMenuItem().addActionListener(e -> handleLanguageSelection("es"));
        this.view.getRoMenuItem().addActionListener(e -> handleLanguageSelection("ro"));

        // Add event listeners to the buttons
        this.view.getLoginButton().addActionListener(e -> handleLogin());
        this.view.getVisitButton().addActionListener(e -> handleVisit());

        updateView();
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

    public void handleLanguageSelection(String languageCode) {
        language.setLanguage(languageCode);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(view, errorMessage, "", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }

    private void updateView() {
        this.view.getTitleLabel().setText(language.getString("login.title"));
        this.view.getSubtitleLabel().setText(language.getString("login.subtitle"));
        this.view.getUsernameLabel().setText(language.getString("login.username"));
        this.view.getPasswordLabel().setText(language.getString("login.password"));
        this.view.getLoginButton().setText(language.getString("login.loginButton"));
        this.view.getVisitButton().setText(language.getString("login.visitButton"));
        this.view.getLanguageMenu().setText(language.getString("menu.language"));
        this.view.getEnMenuItem().setText(language.getString("menu.english"));
        this.view.getFrMenuItem().setText(language.getString("menu.french"));
        this.view.getEsMenuItem().setText(language.getString("menu.spanish"));
        this.view.getRoMenuItem().setText(language.getString("menu.romanian"));
    }
}
