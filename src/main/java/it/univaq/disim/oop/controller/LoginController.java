package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.UtenteService;
import it.univaq.disim.oop.domain.Utente;
import it.univaq.disim.oop.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, DataInitializable<Object> {


    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private Button singUp;

    private ViewDispatcher dispatcher;

    private UtenteService utenteService;

    private CouponBusinessFactory factory;

    public LoginController() {
        factory = CouponBusinessFactory.getInstance();
        utenteService = factory.getUtenteService();
        dispatcher = ViewDispatcher.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.disableProperty().bind(email.textProperty().isEmpty().or(password.textProperty().isEmpty()));
    }
    @Override
    public void initializeData(Object o) {
    }

    @FXML
    void loginAction(Event event) {
        try {
            Utente utente = utenteService.authenticate(email.getText(), password.getText());
            dispatcher.loggedIn(utente);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signUpAction(ActionEvent event) {
        dispatcher.signUp();
    }


}
