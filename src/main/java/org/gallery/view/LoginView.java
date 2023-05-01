package org.gallery.view;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginView extends JFrame {

    private JMenuBar menuBar;
    private JMenu languageMenu;
    private JMenuItem enMenuItem, frMenuItem, esMenuItem, roMenuItem;
    private JLabel subtitleLabel, titleLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    protected JToggleButton loginButton, visitButton;
    protected JSeparator separator1, separator2;

    public LoginView() {
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

        // Set the window title & context specific text
        setTitle("");

        // Configure UI fields & labels
        titleLabel = new JLabel("Log in to your account");
        subtitleLabel = new JLabel("Welcome to Art Gallery");
        usernameLabel = new JLabel("Username");
        usernameField = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        separator1 = new JSeparator();
        separator2 = new JSeparator();

        // Configure action fields & buttons
        loginButton = new JToggleButton("Login");
        visitButton = new JToggleButton("Visit Gallery");

        // Customise the elements
        titleLabel.setFont(new java.awt.Font("New York Extra Large", 1, 24));
        subtitleLabel.setFont(new java.awt.Font("New York Extra Large", 1, 14));

        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usernameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        passwordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        usernameLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        usernameField.setMinimumSize(new java.awt.Dimension(150, 23));
        passwordLabel.setMinimumSize(new java.awt.Dimension(75, 23));
        passwordField.setMinimumSize(new java.awt.Dimension(150, 23));

        usernameLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        usernameField.setPreferredSize(new java.awt.Dimension(150, 23));
        passwordLabel.setPreferredSize(new java.awt.Dimension(75, 23));
        passwordField.setPreferredSize(new java.awt.Dimension(150, 23));

        usernameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        passwordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));


        // Format the panel
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(separator1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(subtitleLabel)
                                .addGap(10, 10, 10)
                                .addComponent(separator2, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                .addContainerGap(30, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(titleLabel)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(loginButton)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(visitButton)
                                                .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(subtitleLabel)
                                        .addComponent(separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(titleLabel)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(usernameLabel))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordLabel))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginButton)
                                        .addComponent(visitButton))
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getSubtitleLabel() {
        return subtitleLabel;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JToggleButton getLoginButton() {
        return loginButton;
    }

    public JToggleButton getVisitButton() {
        return visitButton;
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