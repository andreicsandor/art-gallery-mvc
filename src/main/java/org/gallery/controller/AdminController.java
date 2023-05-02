package org.gallery.controller;

import org.gallery.model.Account;
import org.gallery.model.Gallery;
import org.gallery.model.Language;
import org.gallery.model.repository.AccountRepository;
import org.gallery.model.repository.GalleryRepository;
import org.gallery.view.AdminView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class AdminController implements Observer {
    final private AccountRepository accountRepository;
    final private GalleryRepository galleryRepository;
    final private AdminView view;
    final private Language language;

    public AdminController() {
        this.accountRepository = new AccountRepository();
        this.galleryRepository = new GalleryRepository();
        this.view = new AdminView();
        this.language = new Language();
        language.addObserver(this);

        // Initialise fields
        this.handleRead();
        this.configureGalleryField();

        // Add event listeners to the language menu items
        this.view.getEnMenuItem().addActionListener(e -> handleLanguageSelection("en"));
        this.view.getFrMenuItem().addActionListener(e -> handleLanguageSelection("fr"));
        this.view.getEsMenuItem().addActionListener(e -> handleLanguageSelection("es"));
        this.view.getRoMenuItem().addActionListener(e -> handleLanguageSelection("ro"));

        // Add event listeners to the buttons
        this.view.getTable().getSelectionModel().addListSelectionListener(e -> updateFields());
        this.view.getRoleField().addActionListener(e -> configureGalleryField());
        this.view.getCreateButton().addActionListener(e -> handleCreate());
        this.view.getUpdateButton().addActionListener(e -> handleUpdate());
        this.view.getDeleteButton().addActionListener(e -> handleDelete());

        updateView();
    }

    public void handleRead() {
        DefaultTableModel tableModel = this.view.getTableModel();

        // Clear the table
        tableModel.setRowCount(0);

        // Create a list of available exhibits in the repository
        List<Account> accounts = getSortedAccounts();

        // Add account data to the tableModel
        updateTableModel(accounts);

        // Refresh components
        this.view.getTable().repaint();
        this.refreshButtons();
    }

    public void handleCreate() {
        String firstName = this.view.getNameField().getText();
        String lastName = this.view.getSurnameField().getText();
        String role = (String) this.view.getRoleField().getSelectedItem();
        String username = this.view.getUsernameField().getText();
        String password = this.view.getPasswordField().getText();
        String galleryName = (String) this.view.getGalleryField().getSelectedItem();

        Account existingAccount = accountRepository.findByUsername(username);
        if (existingAccount != null) {
            this.showError(language.getString("error.usernameTaken"));
            return;
        }

        try {
            Account account = new Account(firstName, lastName, role, username, password);
            accountRepository.create(account);

            Gallery gallery = galleryRepository.findByName(galleryName);

            // Add the employee to the gallery if role criteria is met
            // Update the gallery in the database
            if (role.equals("Employee")) {
                gallery.addEmployee(account);
                galleryRepository.update(gallery);
            }
        } catch (Exception e) {
            this.showError(language.getString("error.createAccount"));
        }

        this.showMessage(language.getString("message.createAccount"));

        // Refresh the table
        handleRead();
    }

    public void handleUpdate() {
        Long id = Long.parseLong(this.view.getIdField().getText());
        String firstName = this.view.getNameField().getText();
        String lastName = this.view.getSurnameField().getText();
        String role = (String) this.view.getRoleField().getSelectedItem();
        String username = this.view.getUsernameField().getText();
        String password = this.view.getPasswordField().getText();
        String galleryName = (String) this.view.getGalleryField().getSelectedItem();

        try {
            // Find the searched account & gallery
            Account managedAccount = accountRepository.findById(id);
            Gallery oldGallery = new Gallery();
            Gallery newGallery = galleryRepository.findByName(galleryName);

            if (managedAccount.getRole().equals("Employee")) {
                oldGallery = galleryRepository.findByEmployee(managedAccount);
                // Remove the old account from the gallery
                oldGallery.removeEmployee(managedAccount);
            }

            // Update the managedAccount object with the new values
            managedAccount.setFirstName(firstName);
            managedAccount.setLastName(lastName);
            managedAccount.setRole(role);
            managedAccount.setUsername(username);
            managedAccount.setPassword(password);

            // Add the employee back to the gallery
            // Update the gallery in the database
            if (managedAccount.getRole().equals("Employee")) {
                // Check if the previous account has a gallery associated or not
                if (oldGallery.getName() == null || !oldGallery.getName().equals(galleryName)) {
                    newGallery.addEmployee(managedAccount);
                    galleryRepository.update(newGallery);
                } else {
                    oldGallery.addEmployee(managedAccount);
                    galleryRepository.update(oldGallery);
                }
            }

            // Update the account in the database
            accountRepository.update(managedAccount);

        } catch (Exception e) {
            this.showError(language.getString("error.updateAccount"));
        }

        this.showMessage(language.getString("message.updateAccount"));

        // Refresh components
        handleRead();
    }

    public void handleDelete() {
        Long id = Long.parseLong(this.view.getIdField().getText());

        try {
            // Find the searched account & gallery
            Account managedAccount = accountRepository.findById(id);
            Gallery gallery = new Gallery();

            // Remove the employee from the gallery
            if (managedAccount.getRole().equals("Employee")) {
                gallery = galleryRepository.findByEmployee(managedAccount);
                // Remove the employee from the gallery
                gallery.removeEmployee(managedAccount);
            }

            // Delete the selected account
            accountRepository.delete(managedAccount);

            // Update the gallery in the database
            if (managedAccount.getRole().equals("Employee"))
                galleryRepository.update(gallery);

        } catch (Exception e) {
            this.showError(language.getString("error.deleteAccount"));
        }

        this.showMessage(language.getString("message.deleteAccount"));

        // Refresh the table
        handleRead();
    }

    public void updateFields() {
        JTable table = this.view.getTable();
        DefaultTableModel tableModel = this.view.getTableModel();

        int row = table.getSelectedRow();
        if (row != -1 && row < tableModel.getRowCount()) {
            String id = tableModel.getValueAt(row, 0).toString();
            String firstName = tableModel.getValueAt(row, 1).toString();
            String lastName = tableModel.getValueAt(row, 2).toString();
            String username = tableModel.getValueAt(row, 3).toString();
            String password = tableModel.getValueAt(row, 4).toString();
            String role = tableModel.getValueAt(row, 5).toString();
            String galleryName = tableModel.getValueAt(row, 6).toString();

            String fullName = firstName + " " + lastName;

            this.view.getTitleLabel().setText(fullName);
            this.view.getIdField().setText(id);
            this.view.getNameField().setText(firstName);
            this.view.getSurnameField().setText(lastName);
            this.view.getUsernameField().setText(username);
            this.view.getPasswordField().setText(password);
            this.view.getRoleField().setSelectedItem(role);
            this.view.getGalleryField().setSelectedItem(galleryName);
        }
    }

    public void updateTableModel(List<Account> accounts) {
        DefaultTableModel tableModel = this.view.getTableModel();
        String galleryName;

        // Add element data to the tableModel
        for (Account account : accounts) {
            Gallery gallery = galleryRepository.findByEmployee(account);
            if (gallery == null)
                galleryName = "—";
            else
                galleryName = galleryRepository.findByEmployee(account).getName();
            Object[] rowData = {account.getId(), account.getFirstName(), account.getLastName(), account.getUsername(), account.getPassword(), account.getRole(), galleryName};
            tableModel.addRow(rowData);
        }
    }

    public void configureGalleryField() {
        List<String> galleries = getGalleryNames();
        view.getGalleryField().removeAllItems();
        for (String gallery : galleries) {
            view.getGalleryField().addItem(gallery);
        }

        // Check the role selection
        if (Objects.equals((String) this.view.getRoleField().getSelectedItem(), "Employee")) {
            this.enableGalleryField();
            this.view.getGalleryField().setSelectedItem("The Museum of Modern Art");
        } else {
            this.disableGalleryField();
        }
    }

    public List<Account> getSortedAccounts() {
        List<Account> accountList = accountRepository.listAccounts();

        // Sort the account list by last name, then first name, then role
        accountList.sort(Comparator.comparing(Account::getLastName)
                .thenComparing(Account::getFirstName)
                .thenComparing(Account::getRole));

        return accountList;
    }

    public List<String> getGalleryNames() {
        List<Gallery> galleryList = galleryRepository.listGalleries();

        List<String> galleries = new ArrayList<>();
        for (Gallery gallery : galleryList) {
            galleries.add(gallery.getName());
        }
        return galleries;
    }

    public void enableGalleryField() {
        this.view.getGalleryField().setEnabled(true);
    }

    public void disableGalleryField() {
        this.view.getGalleryField().setEnabled(false);
        this.view.getGalleryField().setSelectedItem(null); // Set default value
    }

    public void refreshButtons() {
        this.view.getCreateButton().setSelected(false);
        this.view.getUpdateButton().setSelected(false);
        this.view.getDeleteButton().setSelected(false);
    }

    public void handleLanguageSelection(String languageCode) {
        language.setLanguage(languageCode);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this.view, message, "", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this.view, errorMessage, "", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }

    private void updateView() {
        this.view.setTitle(language.getString("account.window"));
        this.view.getTitleLabel().setText(language.getString("account.title"));
        this.view.getSubtitleLabel().setText(language.getString("account.subtitle"));
        this.view.getNameLabel().setText(language.getString("account.name"));
        this.view.getSurnameLabel().setText(language.getString("account.surname"));
        this.view.getUsernameLabel().setText(language.getString("account.username"));
        this.view.getPasswordLabel().setText(language.getString("account.password"));
        this.view.getRoleLabel().setText(language.getString("account.role"));
        this.view.getGalleryLabel().setText(language.getString("account.gallery"));
        this.view.getCreateButton().setText(language.getString("account.createButton"));
        this.view.getUpdateButton().setText(language.getString("account.updateButton"));
        this.view.getDeleteButton().setText(language.getString("account.deleteButton"));
        this.view.getLanguageMenu().setText(language.getString("menu.language"));
        this.view.getEnMenuItem().setText(language.getString("menu.english"));
        this.view.getFrMenuItem().setText(language.getString("menu.french"));
        this.view.getEsMenuItem().setText(language.getString("menu.spanish"));
        this.view.getRoMenuItem().setText(language.getString("menu.romanian"));
    }
}
