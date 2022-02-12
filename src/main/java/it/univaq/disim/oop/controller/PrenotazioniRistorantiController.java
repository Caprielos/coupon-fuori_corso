package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.CouponService;
import it.univaq.disim.oop.business.service.PrenotazioneService;
import it.univaq.disim.oop.business.service.RecensioniService;
import it.univaq.disim.oop.domain.Coupon;
import it.univaq.disim.oop.domain.Prenotazione;
import it.univaq.disim.oop.domain.Ristorante;
import it.univaq.disim.oop.domain.Stato;
import it.univaq.disim.oop.view.ViewDispatcher;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrenotazioniRistorantiController implements Initializable, DataInitializable<Ristorante> {

    @FXML
    private TableColumn<Coupon, Button> addButonColumn;

    @FXML
    private TableColumn<Coupon, String> codiceColumn;

    @FXML
    private TableColumn<Coupon, String> nomeColumn;

    @FXML
    private TableView<Coupon> codeTableView;

    @FXML
    private ListView<Prenotazione> prenotazioniList;


    @FXML
    private TextField codiceClienteField;

    @FXML
    private TextField codiceRistoranteField;

    @FXML
    private TextField codiceTextField;

    @FXML
    private Button controllaClienteButton;

    @FXML
    private TextField minutiTextField;

    @FXML
    private TextField oreTextField;

    @FXML
    private DatePicker prenotazioniPicker;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField statoTextField;

    @FXML
    private Button verificaValiditaButton;

    private Prenotazione prenotazione1;

    private Ristorante ristorante;

    private List<Prenotazione> prenotazioneList;

    private List<Coupon> couponList;

    private PrenotazioneService prenotazioneService;

    private CouponService couponService;

    private RecensioniService recensioniService;

    private ViewDispatcher dispatcher;

    private CouponBusinessFactory factory;

    public PrenotazioniRistorantiController() {
        factory = CouponBusinessFactory.getInstance();
        prenotazioneService = factory.getPrenotazioneService();
        couponService = factory.getCouponService();
        recensioniService = factory.getRecensioniService();
        dispatcher = ViewDispatcher.getInstance();
    }

    @FXML
    void listAction(MouseEvent event) {

        prenotazione1 = prenotazioniList.getSelectionModel().getSelectedItem();

        prenotazioniPicker.setValue(prenotazione1.getLocalDate());
        oreTextField.setText(String.valueOf(prenotazione1.getLocalTime().getHour()));
        minutiTextField.setText(String.valueOf(prenotazione1.getLocalTime().getMinute()));
        statoTextField.setText(prenotazione1.getStato().toString());
        codiceTextField.setText(prenotazione1.getCoupon().getCodice());

    }

    @FXML
    void controllaClienteAction(ActionEvent event) {
        codiceClienteField.setText(codiceTextField.getText());
    }

    @FXML
    void verificaValiditaAction(ActionEvent event) {
        if (codiceRistoranteField.getText().equalsIgnoreCase(codiceClienteField.getText())) {
            resultLabel.setText("Codice Corretto");
            prenotazione1.setStato(Stato.VERIFICATO);
            prenotazione1.setStato(Stato.CONSUMATO);
            dispatcher.renderView("prenotazioniRistoranti", ristorante);
        } else {
            resultLabel.setText("Codice Errato");
        }

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


        nomeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Coupon, String> prenotazioneStringCellDataFeatures) {
                return new SimpleStringProperty(prenotazioneStringCellDataFeatures.getValue().getNome());
            }
        });

        codiceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Coupon, String> prenotazioneStringCellDataFeatures) {
                return new SimpleStringProperty(prenotazioneStringCellDataFeatures.getValue().getCodice());
            }
        });

        addButonColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, Button>, ObservableValue<Button>>() {
            @Override
            public ObservableValue<Button> call(TableColumn.CellDataFeatures<Coupon, Button> prenotazioneButtonCellDataFeatures) {
                Button controllaButton = new Button("Controlla");
                controllaButton.setStyle("-fx-background-color:green; -fx-background-radius: 30; -fx-border-radius: 60 ; -fx-border-color: white; -fx-text-fill: white ");
                controllaButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        codiceRistoranteField.setText(prenotazioneButtonCellDataFeatures.getValue().getCodice());
                    }
                });

                return new SimpleObjectProperty<Button>(controllaButton);
            }
        });

    }

    @Override
    public void initializeData(Ristorante ristorante) {
        this.ristorante = ristorante;

        try {
            prenotazioneList = prenotazioneService.cercaPrenotazioniPerRistorante(ristorante);
            ObservableList prenotazioneDataList = FXCollections.observableArrayList(prenotazioneList);
            prenotazioniList.setItems(prenotazioneDataList);

            couponList = couponService.cercaCouponPerRistorante(ristorante);
            ObservableList couponDataList = FXCollections.observableArrayList(couponList);
            codeTableView.setItems(couponDataList);

        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
