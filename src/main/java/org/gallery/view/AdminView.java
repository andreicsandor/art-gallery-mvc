package org.gallery.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminView extends JFrame {

    public static final String[] ROLES = {"Employee", "Administrator"};
    private JMenuBar menuBar;
    private JMenu languageMenu;
    private JMenuItem enMenuItem, frMenuItem, esMenuItem, roMenuItem;
    private JLabel subtitleLabel, titleLabel, nameLabel, surnameLabel, usernameLabel, passwordLabel, roleLabel, galleryLabel;
    private JTextField idField, nameField, surnameField, usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleField, galleryField;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JToggleButton createButton, deleteButton, updateButton;
    private JSeparator separator1, separator2, separator3;

    public AdminView() {
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

        // Create menu components
        menuBar = new JMenuBar();
        languageMenu = new JMenu("Language");
        enMenuItem = new JMenuItem("English");
        frMenuItem = new JMenuItem("French");
        esMenuItem = new JMenuItem("Spanish");
        roMenuItem = new JMenuItem("Romanian");

        // Add menu items to the menu
        languageMenu.add(enMenuItem);
        languageMenu.add(frMenuItem);
        languageMenu.add(esMenuItem);
        languageMenu.add(roMenuItem);

        // Add menu to the menu bar
        menuBar.add(languageMenu);

        // Set the menu bar in the frame
        setJMenuBar(menuBar);

        // Set the window title
        setTitle("Account Management");

        // Configure UI fields & labels
        titleLabel = new JLabel("Manage Account");
        subtitleLabel = new JLabel("Employees & Administrators");
        idField = new JTextField();
        nameLabel = new JLabel("First Name");
        nameField = new JTextField();
        surnameLabel = new JLabel("Last Name");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        surnameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        roleLabel = new JLabel("Role");
        roleField = new JComboBox<>(ROLES);
        galleryLabel = new JLabel("Gallery");
        galleryField = new JComboBox<>();
        separator1 = new JSeparator();
        separator2 = new JSeparator();
        separator3 = new JSeparator();


        // Configure action fields & buttons
        String[] columnNames = {"ID", "First Name", "Last Name", "Username", "Password", "Role", "Gallery"};
        tableModel = new DefaultTableModel(columnNames, 0); // Create a DefaultTableModel for the JTable
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; // Create a JTable with the tableModel
        table.removeColumn(table.getColumnModel().getColumn(0)); // Remove "ID" column from the table
        table.removeColumn(table.getColumnModel().getColumn(3)); // Remove "Password" column from the table
        scrollPane = new JScrollPane(table); // Create a JScrollPane element with accountsTable
        createButton = new JToggleButton("Create");
        updateButton = new JToggleButton("Update");
        deleteButton = new JToggleButton("Delete");


        // Customise the elements
        titleLabel.setFont(new java.awt.Font("New York Extra Large", 1, 24));
        subtitleLabel.setFont(new java.awt.Font("New York Extra Large", 1, 14));

        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        surnameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        surnameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usernameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        passwordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        roleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        galleryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        nameLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        nameField.setMinimumSize(new java.awt.Dimension(150, 23));
        surnameLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        surnameField.setMinimumSize(new java.awt.Dimension(150, 23));
        usernameLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        usernameField.setMinimumSize(new java.awt.Dimension(150, 23));
        passwordLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        passwordField.setMinimumSize(new java.awt.Dimension(150, 23));
        roleLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        roleField.setMinimumSize(new java.awt.Dimension(150, 23));
        galleryLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        galleryField.setMinimumSize(new java.awt.Dimension(230, 23));

        nameLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        nameField.setPreferredSize(new java.awt.Dimension(150, 23));
        surnameLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        surnameField.setPreferredSize(new java.awt.Dimension(150, 23));
        usernameLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        usernameField.setPreferredSize(new java.awt.Dimension(150, 23));
        passwordLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        passwordField.setPreferredSize(new java.awt.Dimension(150, 23));
        roleLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        roleField.setPreferredSize(new java.awt.Dimension(150, 23));
        galleryLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        galleryField.setPreferredSize(new java.awt.Dimension(230, 23));

        nameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        surnameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        usernameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        passwordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));


        // Format the panel
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(separator1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                                .addGap(10, 10, 10)
                                                .addComponent(subtitleLabel)
                                                .addGap(10, 10, 10)
                                                .addComponent(separator2, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                                        .addComponent(scrollPane))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(titleLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(roleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(galleryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(roleField, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addComponent(galleryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 15, Short.MAX_VALUE))
                                                        .addComponent(separator3, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addComponent(createButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(updateButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(deleteButton)))
                                                .addContainerGap(20, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(scrollPane)
                                                .addGap(20, 20, 20))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(titleLabel)
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nameLabel))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(surnameLabel))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(usernameLabel))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passwordLabel))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(roleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(roleLabel))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(galleryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(galleryLabel))
                                                .addGap(52, 52, 52)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(createButton)
                                                        .addComponent(updateButton)
                                                        .addComponent(deleteButton))
                                                .addContainerGap(22, Short.MAX_VALUE))))
        );

        pack();
        setSize(860, 445);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getSubtitleLabel() {
        return subtitleLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getSurnameLabel() {
        return surnameLabel;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JLabel getRoleLabel() {
        return roleLabel;
    }

    public JLabel getGalleryLabel() {
        return galleryLabel;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSurnameField() {
        return surnameField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JComboBox<String> getRoleField() {
        return roleField;
    }

    public JComboBox<String> getGalleryField() {
        return galleryField;
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

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public JMenu getLanguageMenu() {
        return languageMenu;
    }

    public JMenuItem getEnMenuItem() {
        return enMenuItem;
    }

    public JMenuItem getFrMenuItem() {
        return frMenuItem;
    }

    public JMenuItem getEsMenuItem() {
        return esMenuItem;
    }

    public JMenuItem getRoMenuItem() {
        return roMenuItem;
    }
}