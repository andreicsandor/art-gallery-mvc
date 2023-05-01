package org.gallery.controller;

import org.gallery.model.Language;
import org.gallery.view.VisitorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Observable;

public class VisitorController extends GalleryController {
    private final VisitorView view;
    final private Language language;

    public VisitorController() {
        super();
        this.view = new VisitorView();
        this.language = new Language();
        language.addObserver(this);

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

        // Add event listeners to the language menu items
        this.view.getEnMenuItem().addActionListener(e -> handleLanguageSelection("en"));
        this.view.getFrMenuItem().addActionListener(e -> handleLanguageSelection("fr"));
        this.view.getEsMenuItem().addActionListener(e -> handleLanguageSelection("es"));
        this.view.getRoMenuItem().addActionListener(e -> handleLanguageSelection("ro"));

        // Add event listeners to the buttons
        this.view.getTable().getSelectionModel().addListSelectionListener(e -> updateFields());
        this.view.getFilterField().addActionListener(e -> configureCriteriaField());
        this.view.getFilterButton().addActionListener(e -> handleFilter());
        this.view.getClearButton().addActionListener(e -> handleRead());

        updateView();
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

    public void handleLanguageSelection(String languageCode) {
        language.setLanguage(languageCode);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }

    private void updateView() {
        this.view.setTitle(language.getString("gallery.window"));
        this.view.getTitleLabel().setText(language.getString("gallery.title"));
        this.view.getSubtitleLabel().setText(language.getString("gallery.subtitle"));
        this.view.getNameLabel().setText(language.getString("gallery.name"));
        this.view.getArtistLabel().setText(language.getString("gallery.artist"));
        this.view.getYearLabel().setText(language.getString("gallery.year"));
        this.view.getTypeLabel().setText(language.getString("gallery.type"));
        this.view.getGalleryLabel().setText(language.getString("gallery.gallery"));
        this.view.getCreateButton().setText(language.getString("gallery.createButton"));
        this.view.getUpdateButton().setText(language.getString("gallery.updateButton"));
        this.view.getDeleteButton().setText(language.getString("gallery.deleteButton"));
        this.view.getFilterButton().setText(language.getString("gallery.filterButton"));
        this.view.getClearButton().setText(language.getString("gallery.clearButton"));
        this.view.getLanguageMenu().setText(language.getString("menu.language"));
        this.view.getEnMenuItem().setText(language.getString("menu.english"));
        this.view.getFrMenuItem().setText(language.getString("menu.french"));
        this.view.getEsMenuItem().setText(language.getString("menu.spanish"));
        this.view.getRoMenuItem().setText(language.getString("menu.romanian"));
    }
}
