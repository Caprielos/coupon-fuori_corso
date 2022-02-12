package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.PrenotazioneService;
import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Prenotazione;
import it.univaq.disim.oop.domain.Stato;
import it.univaq.disim.oop.view.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
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

    @FXML
    private TextField indirizzoRistoranteTextField;

    @FXML
    private TextField nomeRistoranteTextField;

    @FXML
    private TextField numeroCivicoRistoranteTextField;

    @FXML
    private Button goToRestaurant;

    private Prenotazione prenotazione1;

    private Cliente cliente;

    private List<Prenotazione> prenotazioneList;

    private PrenotazioneService prenotazioneService;

    private ViewDispatcher dispatcher;

    private CouponBusinessFactory factory;

    public PrenotazioniClienteController() {
        factory = CouponBusinessFactory.getInstance();
        prenotazioneService = factory.getPrenotazioneService();
        dispatcher = ViewDispatcher.getInstance();
    }

    @FXML
    void goToRestaurant(ActionEvent event) {
        prenotazione1.setStato(Stato.DA_VERIFICARE);
        prenotazioniList.refresh();
    }


    @FXML
    void listAction(MouseEvent event) {
        prenotazione1 = prenotazioniList.getSelectionModel().getSelectedItem();

        prenotazioniPicker.setValue(prenotazione1.getLocalDate());
        oreTextField.setText(String.valueOf(prenotazione1.getLocalTime().getHour()));
        minutiTextField.setText(String.valueOf(prenotazione1.getLocalTime().getMinute()));
        statoTextField.setText(prenotazione1.getStato().toString());
        codiceTextField.setText(prenotazione1.getCoupon().getCodice());

        nomeRistoranteTextField.setText(prenotazione1.getRistorante().getNome());
        indirizzoRistoranteTextField.setText(prenotazione1.getRistorante().getIndirizzo());
        numeroCivicoRistoranteTextField.setText(prenotazione1.getRistorante().getNumeroCivico());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        prenotazioniList.setCellFactory(new Callback<ListView<Prenotazione>, ListCell<Prenotazione>>() {
            @Override
            public ListCell<Prenotazione> call(ListView<Prenotazione> param) {
                ListCell<Prenotazione> cell = new ListCell<Prenotazione>() {
                    @Override
                    protected void updateItem(Prenotazione item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText("Prenotazione: " + item.getId() + "" + item.getId());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        prenotazioniPicker.setEditable(false);
        oreTextField.setEditable(false);
        minutiTextField.setEditable(false);
        statoTextField.setEditable(false);
        codiceTextField.setEditable(false);

        nomeRistoranteTextField.setEditable(false);
        indirizzoRistoranteTextField.setEditable(false);
        numeroCivicoRistoranteTextField.setEditable(false);

    }

    @Override
    public void initializeData(Cliente cliente) {

        this.cliente = cliente;

        try {
            prenotazioneList = prenotazioneService.cercaPrenotazioniPerCliente(cliente);
            ObservableList prenotazioneDataList = FXCollections.observableArrayList(prenotazioneList);
            prenotazioniList.setItems(prenotazioneDataList);
        } catch (BusinessException e) {
            e.printStackTrace();
        }


    }
}

