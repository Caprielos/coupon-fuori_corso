package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Ristorante;
import it.univaq.disim.oop.domain.Utente;
import it.univaq.disim.oop.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class LayoutController implements Initializable, DataInitializable<Utente> {

    private static final MenuElement MENU_HOME = new MenuElement("Home", "home");

    private static final MenuElement[] MENU_CLIENTE = {new MenuElement("Vedi Coupon", "allCoupon"),};

    private static final MenuElement[] MENU_RISTORANTE = {new MenuElement("Gestione Coupon", "managementCoupon"),
            new MenuElement("Gestione Qualcosa", ""),};


    @FXML
    private VBox menuBar;

    private ViewDispatcher dispatcher;

    private Utente utente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dispatcher = ViewDispatcher.getInstance();
    }

    @Override
    public void initializeData(Utente utente) {
        this.utente = utente;
        menuBar.getChildren().addAll(createButton(MENU_HOME));
        menuBar.getChildren().add(new Separator());

        if (utente instanceof Cliente) {
            for (MenuElement menu : MENU_CLIENTE) {
                menuBar.getChildren().add(createButton(menu));
            }
        }
        if (utente instanceof Ristorante) {
            for (MenuElement menu : MENU_RISTORANTE) {
                menuBar.getChildren().add(createButton(menu));
            }
        }
    }

    private Button createButton(MenuElement viewItem) {
        Button button = new Button(viewItem.getNome());
        button.setStyle("-fx-background-color: transparent; -fx-font-size: 14;");
        button.setTextFill(Paint.valueOf("white"));
        button.setPrefHeight(10);
        button.setPrefWidth(180);
        button.setOnAction((ActionEvent event) -> {
            dispatcher.renderView(viewItem.getVista(), utente);
        });
        return button;
    }
}

