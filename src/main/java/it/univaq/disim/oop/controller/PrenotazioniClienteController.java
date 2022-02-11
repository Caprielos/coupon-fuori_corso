package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Prenotazione;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PrenotazioniClienteController implements Initializable, DataInitializable<Cliente> {

    @FXML
    private TextField codiceTextField;

    @FXML
    private TextField minutiTextField;

    @FXML
    private TextField oreTextField;

    @FXML
    private ListView<Prenotazione> prenotazioniList;

    @FXML
    private DatePicker prenotazioniPicker;

    @FXML
    private TextField statoTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initializeData(Cliente cliente) {
        DataInitializable.super.initializeData(cliente);
    }
}

