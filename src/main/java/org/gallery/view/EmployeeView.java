package org.gallery.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EmployeeView extends JFrame {
    public static final String[] FILTER = {"Type", "Artist"};
    public static final String[] TYPES = {"Painting", "Sculpture"};
    protected JLabel subtitleLabel, titleLabel, nameLabel, artistLabel, yearLabel, typeLabel, galleryLabel;
    protected JTextField idField, nameField, artistField, yearField, filterSearchField;
    protected JComboBox<String> typeField, galleryField, filterTypeField, filterCriteriaField;
    protected DefaultTableModel tableModel;
    protected JTable table;
    protected JScrollPane scrollPane;
    protected JToggleButton createButton, deleteButton, updateButton, filterButton, clearButton;
    protected JSeparator separator1, separator2, separator3;

    public EmployeeView() {
        try {
            // Set the macOS system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Initialise the components
        initialiseComponents();
    }

    protected void initialiseComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });

        // Set the window title & context specific text
        setTitle("Art Gallery — Exhibits");

        // Configure UI fields & labels
        titleLabel = new JLabel("Exhibit Details");
        subtitleLabel = new JLabel("Exhibits");
        idField = new JTextField();
        nameLabel = new JLabel("Name");
        nameField = new JTextField();
        artistLabel = new JLabel("Artist");
        artistField = new JTextField();
        yearLabel = new JLabel("Year");
        yearField = new JTextField();
        typeLabel = new JLabel("Type");
        typeField = new JComboBox<>(TYPES);
        galleryLabel = new JLabel("Gallery");
        galleryField = new JComboBox<>();
        separator1 = new JSeparator();
        separator2 = new JSeparator();
        separator3 = new JSeparator();


        // Configure action fields & buttons
        String[] columnNames = {"ID", "Name", "Artist", "Year", "Type", "Gallery"};
        tableModel = new DefaultTableModel(columnNames, 0); // Create a DefaultTableModel for the JTable
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; // Create a JTable with the tableModel
        table.removeColumn(table.getColumnModel().getColumn(0)); // Remove "ID" column from the table
        scrollPane = new JScrollPane(table); // Create a JScrollPane element with table
        filterTypeField = new JComboBox<>(FILTER);
        filterCriteriaField = new JComboBox<>();
        filterSearchField = new JTextField();
        createButton = new JToggleButton("Create");
        filterButton = new JToggleButton("Filter");
        updateButton = new JToggleButton("Update");
        deleteButton = new JToggleButton("Delete");
        clearButton = new JToggleButton("Clear");


        // Customise the elements
        titleLabel.setFont(new java.awt.Font("New York Extra Large", 1, 24));
        subtitleLabel.setFont(new java.awt.Font("New York Extra Large", 1, 14));

        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        artistLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        artistField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        yearLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        typeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        galleryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        filterSearchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        nameLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        nameField.setMinimumSize(new java.awt.Dimension(175, 23));
        artistLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        artistField.setMinimumSize(new java.awt.Dimension(175, 23));
        yearLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        yearField.setMinimumSize(new java.awt.Dimension(175, 23));
        typeLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        typeField.setMinimumSize(new java.awt.Dimension(150, 23));
        galleryLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        galleryField.setMinimumSize(new java.awt.Dimension(230, 23));

        nameLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        nameField.setPreferredSize(new java.awt.Dimension(250, 23));
        artistLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        artistField.setPreferredSize(new java.awt.Dimension(250, 23));
        yearLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        yearField.setPreferredSize(new java.awt.Dimension(250, 23));
        typeLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        typeField.setPreferredSize(new java.awt.Dimension(150, 23));
        galleryLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        galleryField.setPreferredSize(new java.awt.Dimension(230, 23));

        nameField.setDisabledTextColor(Color.BLACK);
        artistField.setDisabledTextColor(Color.BLACK);
        yearField.setDisabledTextColor(Color.BLACK);

        nameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        artistField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        yearField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));


        // Format the panel
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(separator1)
                                                .addGap(10, 10, 10)
                                                .addComponent(subtitleLabel)
                                                .addGap(10, 10, 10)
                                                .addComponent(separator2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(filterTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(filterCriteriaField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(titleLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(createButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(updateButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(deleteButton))
                                                        .addComponent(separator3, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(artistLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(galleryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(artistField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(galleryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 17, Short.MAX_VALUE)))
                                                .addContainerGap(20, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(subtitleLabel)
                                        .addComponent(separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(separator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(filterTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(filterCriteriaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(clearButton)
                                                        .addComponent(filterButton))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scrollPane)
                                                .addGap(20, 20, 20))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(titleLabel)
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nameLabel))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(artistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(artistLabel))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(yearLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(typeLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(galleryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(galleryLabel))
                                                .addGap(50, 50, 50)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(createButton)
                                                        .addComponent(updateButton)
                                                        .addComponent(deleteButton))
                                                .addContainerGap(20, Short.MAX_VALUE))))
        );

        pack();
        setSize(900, 390);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getArtistField() {
        return artistField;
    }

    public JTextField getYearField() {
        return yearField;
    }

    public JComboBox<String> getTypeField() {
        return typeField;
    }

    public JComboBox<String> getGalleryField() {
        return galleryField;
    }

    public JComboBox<String> getFilterField() {
        return filterTypeField;
    }

    public JComboBox<String> getCriteriaField () {
        return filterCriteriaField;
    }

    public JToggleButton getCreateButton() {
        return createButton;
    }

    public JToggleButton getUpdateButton() {
        return updateButton;
    }

    public JToggleButton getDeleteButton() {
        return deleteButton;
    }

    public JToggleButton getFilterButton() {
        return filterButton;
    }

    public JToggleButton getClearButton() {
        return clearButton;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }
}
