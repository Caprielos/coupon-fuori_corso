package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.UtenteService;
import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Ristorante;
import it.univaq.disim.oop.domain.Utente;
import it.univaq.disim.oop.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable, DataInitializable<Object> {

    @FXML
    private CheckBox ristoranteBox;

    @FXML
    private CheckBox clienteBox;

    @FXML
    private TextField cognomeClienteTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField indirizzoRistoranteTextField;

    @FXML
    private TextField nomeClienteTextField;

    @FXML
    private TextField nomeRistoranteTextField;

    @FXML
    private TextField numeroCivicoRistoranteTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registratiButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private ImageView backImg;

    private ViewDispatcher dispatcher;

    private UtenteService utenteService;

    private Utente utente;

    private Cliente cliente;

    private Ristorante ristorante;

    private Object object;

    private CouponBusinessFactory factory;

    public RegisterController() {
        factory = CouponBusinessFactory.getInstance();
        utenteService = factory.getUtenteService();
        dispatcher = ViewDispatcher.getInstance();
    }

    @FXML
    void registratiAction(ActionEvent event) {
        try {
            if (clienteBox.isSelected()) {
                this.cliente = new Cliente();

                this.cliente.setNome(nomeClienteTextField.getText());
                this.cliente.setCognome(cognomeClienteTextField.getText());

                this.cliente.setEmail(emailTextField.getText());
                this.cliente.setUsername(usernameTextField.getText());
                this.cliente.setPassword(passwordField.getText());

                dispatcher.logout();
            }
            utenteService.recordUser(this.cliente);
        } catch (BusinessException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.utente = new Utente();

        backImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dispatcher.logout();
            }
        });

    }

    @FXML
    void clienteBoxAction(ActionEvent event) {
        this.object = new Cliente();
        nomeRistoranteTextField.setVisible(false);
        indirizzoRistoranteTextField.setVisible(false);
        numeroCivicoRistoranteTextField.setVisible(false);
    }

    @FXML
    void ristoranteBoxAction(ActionEvent event) {
        this.object = new Ristorante();
        cognomeClienteTextField.setVisible(false);
        nomeClienteTextField.setVisible(false);
    }

    @Override
    public void initializeData(Object o) {

    }
}

