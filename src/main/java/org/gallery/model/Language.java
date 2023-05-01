package org.gallery.model;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Observable;

public class Language extends Observable {
    private ResourceBundle resourceBundle;

    public Language() {
        this.resourceBundle = ResourceBundle.getBundle("messages", new Locale("en"));
    }

    public void setLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        resourceBundle = ResourceBundle.getBundle("messages", locale);
        setChanged();
        notifyObservers();
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}