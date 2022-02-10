package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.domain.Utente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LayoutController implements Initializable, DataInitializable<Utente> {

    private static final MenuElement MENU_HOME = new MenuElement("Home", "home");




    @FXML
    private VBox menuBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initializeData(Utente utente) {
        DataInitializable.super.initializeData(utente);
    }
}

