package org.gallery.controller;

import org.gallery.model.Exhibit;
import org.gallery.model.Gallery;
import org.gallery.model.repository.ExhibitRepository;
import org.gallery.model.repository.GalleryRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

import static org.gallery.view.VisitorView.TYPES;

public abstract class GalleryController implements Observer {
    protected final ExhibitRepository exhibitRepository;
    protected final GalleryRepository galleryRepository;

    public GalleryController() {
        this.exhibitRepository = new ExhibitRepository();
        this.galleryRepository = new GalleryRepository();
    }

    protected void commonHandleRead(DefaultTableModel tableModel) {
        // Clear the table
        tableModel.setRowCount(0);

        // Create a list of available exhibits in the repository
        List<Exhibit> exhibits = commonGetSortedExhibits();

        // Add exhibit data to the tableModel
        commonUpdateTableModel(exhibits, tableModel);
    }

    protected void commonHandleFilter(String selectedFilter, String selectedCriteria, DefaultTableModel tableModel) {
        // Clear the table
        tableModel.setRowCount(0);

        List<Exhibit> exhibits;
        if (Objects.equals(selectedFilter, "Type")) {
            // Create a list of available exhibits in the repository
            exhibits = exhibitRepository.filterExhibitsByType(selectedCriteria);
            // Add account data to the tableModel
            commonUpdateTableModel(exhibits, tableModel);
        }
        else {
            // Create a list of available exhibits in the repository
            exhibits = exhibitRepository.filterExhibitsByArtist(selectedCriteria);
            // Add account data to the tableModel
            commonUpdateTableModel(exhibits, tableModel);
        }
    }

    protected void commonUpdateFields(DefaultTableModel tableModel, JTable table, JLabel titleLabel, JTextField idField, JTextField nameField, JTextField artistField, JTextField yearField, JComboBox<String> typeField, JComboBox<String> galleryField) {
        int row = table.getSelectedRow();
        if (row != -1 && row < tableModel.getRowCount()) {
            String id = tableModel.getValueAt(row, 0).toString();
            String name = tableModel.getValueAt(row, 1).toString();
            String artist = tableModel.getValueAt(row, 2).toString();
            String year = tableModel.getValueAt(row, 3).toString();
            String type = tableModel.getValueAt(row, 4).toString();
            String galleryName = tableModel.getValueAt(row, 5).toString();

            titleLabel.setText(name);
            idField.setText(id);
            nameField.setText(name);
            artistField.setText(artist);
            yearField.setText(year);
            typeField.setSelectedItem(type);
            galleryField.setSelectedItem(galleryName);
        }
    }

    protected void commonConfigureCriteriaField(String selectedFilter, JComboBox criteriaField) {
        if (Objects.equals(selectedFilter, "Type")) {
            criteriaField.removeAllItems();
            for (String type : TYPES) {
                criteriaField.addItem(type);
            }
        }
        else {
            List<Exhibit> exhibits = commonGetSortedExhibits();

            // Create a set that removes duplicates
            Set<String> artists = new HashSet<>();
            for (Exhibit exhibit : exhibits) {
                artists.add(exhibit.getArtist());
            }

            // Populate the dropdown menu
            criteriaField.removeAllItems();
            for (String artist : artists) {
                criteriaField.addItem(artist);
            }
        }
    }

    protected void commonConfigureGalleryField(JComboBox galleryField) {
        List<String> galleries = commonGetGalleryNames();
        galleryField.removeAllItems();
        for (String gallery : galleries) {
            galleryField.addItem(gallery);
        }
    }

    protected void commonUpdateTableModel(List<Exhibit> exhibits, DefaultTableModel tableModel) {
        // Add element data to the tableModel
        for (Exhibit exhibit : exhibits) {
            Object[] rowData = {exhibit.getId(), exhibit.getName(), exhibit.getArtist(), exhibit.getYear(), exhibit.getType(), galleryRepository.findByExhibit(exhibit).getName()};
            tableModel.addRow(rowData);
        }
    }

    protected List<Exhibit> commonGetSortedExhibits() {
        List<Exhibit> exhibitList = exhibitRepository.listExhibits();

        // Sort the exhibit list by name, then artist
        exhibitList.sort(Comparator.comparing(Exhibit::getYear)
                .thenComparing(Exhibit::getArtist)
                .thenComparing(Exhibit::getName));

        return exhibitList;
    }

    protected List<String> commonGetGalleryNames() {
        List<Gallery> galleryList = galleryRepository.listGalleries();

        List<String> galleries = new ArrayList<>();
        for (Gallery gallery : galleryList) {
            galleries.add(gallery.getName());
        }
        return galleries;
    }
}
