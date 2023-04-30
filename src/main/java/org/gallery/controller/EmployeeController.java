package org.gallery.controller;

import org.gallery.model.Exhibit;
import org.gallery.model.Gallery;
import org.gallery.view.EmployeeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Observable;

public class EmployeeController extends GalleryController {
    private final EmployeeView view;

    public EmployeeController() {
        super();
        this.view = new EmployeeView();

        // Initialise fields
        this.handleRead();
        this.configureGalleryField();
        this.configureCriteriaField();

        // Add event listeners to the buttons
        this.view.getTable().getSelectionModel().addListSelectionListener(e -> updateFields());
        this.view.getFilterField().addActionListener(e -> configureCriteriaField());
        this.view.getCreateButton().addActionListener(e -> handleCreate());
        this.view.getUpdateButton().addActionListener(e -> handleUpdate());
        this.view.getDeleteButton().addActionListener(e -> handleDelete());
        this.view.getFilterButton().addActionListener(e -> handleFilter());
        this.view.getClearButton().addActionListener(e -> handleRead());
    }

    public void handleRead() {
        DefaultTableModel tableModel = this.view.getTableModel();

        // Call the method from the common Gallery Controller
        commonHandleRead(tableModel);

        // Refresh components
        this.view.getTable().repaint();
        this.refreshButtons();
    }

    public void handleCreate() {
        String name = this.view.getNameField().getText();
        String artist = this.view.getArtistField().getText();
        int year = Integer.parseInt(this.view.getYearField().getText());
        String exhibitType = (String) this.view.getTypeField().getSelectedItem();
        String galleryName = (String) this.view.getGalleryField().getSelectedItem();

        Exhibit existingExhibit = exhibitRepository.findByNameArtist(name, artist);
        if (existingExhibit != null) {
            String errorMessage = String.format("Exhibit '%s' by '%s' is already registered.", name, artist);
            this.showError(errorMessage);
            return;
        }

        try {
            Exhibit exhibit = new Exhibit(name, artist, exhibitType, year);
            exhibitRepository.create(exhibit);

            Gallery gallery = galleryRepository.findByName(galleryName);

            // Add the exhibit to the gallery
            // Update the gallery in the database
            gallery.addExhibit(exhibit);
            galleryRepository.update(gallery);
        } catch (Exception e) {
            this.showError("Failed to create a new exhibit.");
        }

        this.showMessage("Exhibit created successfully.");

        // Refresh the table
        handleRead();
    }

    public void handleUpdate() {
        Long id = Long.parseLong(this.view.getIdField().getText());
        String name = this.view.getNameField().getText();
        String artist = this.view.getArtistField().getText();
        int year = Integer.parseInt(this.view.getYearField().getText());
        String exhibitType = (String) this.view.getTypeField().getSelectedItem();
        String galleryName = (String) this.view.getGalleryField().getSelectedItem();

        try {
            // Find the searched exhibit & gallery
            Exhibit managedExhibit = exhibitRepository.findById(id);
            Gallery oldGallery;
            Gallery newGallery = galleryRepository.findByName(galleryName);

            oldGallery = galleryRepository.findByExhibit(managedExhibit);
            // Remove the old exhibit from the gallery
            oldGallery.removeExhibit(managedExhibit);

            // Update the managedExhibit object with the new values
            managedExhibit.setName(name);
            managedExhibit.setArtist(artist);
            managedExhibit.setYear(year);
            managedExhibit.setType(exhibitType);

            // Add the exhibit back to the gallery
            // Update the gallery in the database
            // Check if the previous exhibit has a gallery associated or not
            if (oldGallery.getName() == null || !oldGallery.getName().equals(galleryName)) {
                newGallery.addExhibit(managedExhibit);
                galleryRepository.update(newGallery);
            } else {
                oldGallery.addExhibit(managedExhibit);
                galleryRepository.update(oldGallery);
            }

            // Update the exhibit in the database
            exhibitRepository.update(managedExhibit);

        } catch (Exception e) {
            this.showError("Failed to update exhibit.");
        }

        this.showMessage("Exhibit updated successfully.");

        // Refresh the table
        handleRead();
    }

    public void handleDelete() {
        Long id = Long.parseLong(this.view.getIdField().getText());

        try {
            // Find the searched exhibit
            Exhibit managedExhibit = exhibitRepository.findById(id);

            // Remove the exhibit from the gallery
            Gallery gallery = galleryRepository.findByExhibit(managedExhibit);
            // Remove the employee from the gallery
            gallery.removeExhibit(managedExhibit);

            // Delete the selected account
            exhibitRepository.delete(managedExhibit);

            // Update the gallery in the database
            galleryRepository.update(gallery);

        } catch (Exception e) {
            this.showError("Failed to delete exhibit.");
        }

        this.showMessage("Exhibit deleted successfully.");

        // Refresh the table
        handleRead();
    }

    public void handleFilter() {
        String selectedFilter = (String) this.view.getFilterField().getSelectedItem();
        String selectedCriteria = (String) this.view.getCriteriaField().getSelectedItem();
        DefaultTableModel tableModel = view.getTableModel();

        // Call the method from the common Gallery Controller
        commonHandleFilter(selectedFilter, selectedCriteria, tableModel);

        // Refresh components
        this.view.getTable().repaint();
        this.refreshFields();
        this.refreshButtons();
    }

    public void updateFields() {
        JTable table = this.view.getTable();
        DefaultTableModel tableModel = this.view.getTableModel();
        JLabel titleLabel = this.view.getTitleLabel();
        JTextField idField = this.view.getIdField();
        JTextField nameField = this.view.getNameField();
        JTextField artistField = this.view.getArtistField();
        JTextField yearField = this.view.getYearField();
        JComboBox<String> typeField = this.view.getTypeField();
        JComboBox<String> galleryField = this.view.getGalleryField();

        // Call the method from the common Gallery Controller
        commonUpdateFields(tableModel, table, titleLabel, idField, nameField, artistField, yearField, typeField, galleryField);
    }

    public void configureCriteriaField() {
        String selectedFilter = (String) this.view.getFilterField().getSelectedItem();
        JComboBox<String> criteriaField = this.view.getCriteriaField();

        // Call the method from the common Gallery Controller
        commonConfigureCriteriaField(selectedFilter, criteriaField);
    }

    public void configureGalleryField() {
        JComboBox<String> galleryField = this.view.getGalleryField();

        // Call the method from the common Gallery Presenter
        commonConfigureGalleryField(galleryField);
    }

    public void refreshFields() {
        this.view.getTitleLabel().setText("Exhibit Details");
        this.view.getIdField().setText("");
        this.view.getNameField().setText("");
        this.view.getArtistField().setText("");
        this.view.getYearField().setText("");
        this.view.getTypeField().setSelectedIndex(0);
        this.view.getGalleryField().setSelectedIndex(0);
    }

    public void refreshButtons() {
        this.view.getCreateButton().setSelected(false);
        this.view.getUpdateButton().setSelected(false);
        this.view.getDeleteButton().setSelected(false);
        this.view.getFilterButton().setSelected(false);
        this.view.getClearButton().setSelected(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this.view, message, "", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this.view, errorMessage, "", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
