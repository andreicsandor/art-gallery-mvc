package org.gallery;

import org.gallery.controller.LoginController;

public class App
{
    public static void main( String[] args ) {
        /*
        // Create instance of repository
        AccountRepository aRepository = new AccountRepository();
        ExhibitRepository eRepository = new ExhibitRepository();
        GalleryRepository gRepository = new GalleryRepository();

        // Create instance of account
        Account account1 = new Account("John", "Appleseed", "Administrator", "ajohn", "password");
        // Create account
        aRepository.create(account1);
        System.out.println("Added user " + account1);

        // Create instance of account
        Account account2 = new Account("Jane", "Appleseed", "Employee", "ajane", "password");
        // Create account
        aRepository.create(account2);
        System.out.println("Added user " + account2);

        // Create instance of gallery
        Gallery gallery1 = new Gallery("The Museum of Modern Art", "New York City, New York, USA");
        // Create gallery
        gRepository.create(gallery1);
        System.out.println("Added gallery " + gallery1);

        // Create instance of gallery
        Gallery gallery2 = new Gallery("Louvre Museum", "Paris, France");
        // Create gallery
        gRepository.create(gallery2);
        System.out.println("Added gallery " + gallery2);

        // Add employee to gallery
        gallery1.addEmployee(account2);
        gRepository.update(gallery1);

        // Create instance of exhibit
        Exhibit exhibit1 = new Exhibit("De sterrennacht", "Vincent van Gogh", "Painting", 1889);
        // Create exhibit
        eRepository.create(exhibit1);
        System.out.println("Added exhibit " + exhibit1);

        // Create instance of exhibit
        Exhibit exhibit2 = new Exhibit("The Mona Lisa", "Leonardo da Vinci", "Painting", 1506);
        // Create exhibit
        eRepository.create(exhibit2);
        System.out.println("Added exhibit " + exhibit2);

        // Create instance of exhibit
        Exhibit exhibit3 = new Exhibit("The Thinker", "Auguste Rodin", "Sculpture", 1904);
        // Create exhibit
        eRepository.create(exhibit3);
        System.out.println("Added exhibit " + exhibit3);

        // Create instance of exhibit
        Exhibit exhibit4 = new Exhibit("The Virgin & Laughing Child", "Leonardo Da Vinci", "Sculpture", 1472);
        // Create exhibit
        eRepository.create(exhibit4);
        System.out.println("Added exhibit " + exhibit4);

        // Add exhibit to gallery
        gallery1.addExhibit(exhibit1);
        gRepository.update(gallery1);

        // Add exhibit to gallery
        gallery2.addExhibit(exhibit2);
        gRepository.update(gallery2);

        // Add exhibit to gallery
        gallery1.addExhibit(exhibit3);
        gRepository.update(gallery1);

        // Add exhibit to gallery
        gallery2.addExhibit(exhibit4);
        gRepository.update(gallery2);
        */

        // Create the login controller
        LoginController loginWindow = new LoginController();

    }
}