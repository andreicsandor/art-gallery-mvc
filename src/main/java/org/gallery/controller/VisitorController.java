package org.gallery.controller;

import org.gallery.view.VisitorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Observable;

public class VisitorController extends GalleryController {
    private final VisitorView view;

    public VisitorController() {
        super();
        this.view = new VisitorView();

        // Initialise fields
        this.handleRead();
        this.configureGalleryField();
        this.configureCriteriaField();
        this.view.getCreateButton().setVisible(false);
        this.view.getUpdateButton().setVisible(false);
        this.view.getDeleteButton().setVisible(false);
        this.view.getNameField().setEnabled(false);
        this.view.getArtistField().setEnabled(false);
        this.view.getYearField().setEnabled(false);
        this.view.getTypeField().setEnabled(false);
        this.view.getGalleryField().setEnabled(false);

        // Add event listeners to the buttons
        this.view.getTable().getSelectionModel().addListSelectionListener(e -> updateFields());
        this.view.getFilterField().addActionListener(e -> configureCriteriaField());
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

    @Override
    public void update(Observable o, Object arg) {

    }
}
