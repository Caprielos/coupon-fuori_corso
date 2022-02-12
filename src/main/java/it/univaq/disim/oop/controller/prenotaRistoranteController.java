package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.CouponService;
import it.univaq.disim.oop.business.service.PrenotazioneService;
import it.univaq.disim.oop.business.service.RecensioniService;
import it.univaq.disim.oop.domain.*;
import it.univaq.disim.oop.view.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class prenotaRistoranteController implements Initializable, DataInitializable<Coupon> {

    @FXML
    private DatePicker dataPrenotazionePicker;

    @FXML
    private TextArea descrizioneCouponArea;

    @FXML
    private TextField indirizzoRistoranteTextField;

    @FXML
    private TextField minutiTextField;

    @FXML
    private TextField nomeCouponTextField;

    @FXML
    private TextField nomeRistoranteTextField;

    @FXML
    private TextField numeroCivicoRistoranteTextField;

    @FXML
    private TextField numeroPersonePrenotazioneTextField;

    @FXML
    private TextField oreTextField;

    @FXML
    private Button prenotaButton;

    @FXML
    private ListView<Recensione> recensioniListView;

    @FXML
    private TextField valoreScontoCouponTextField;

    @FXML
    private CheckBox voglioPrenotareCheckBox;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private Text text3;

    @FXML
    private ImageView backImg;

    private ViewDispatcher dispatcher;

    private Cliente cliente;

    private Ristorante ristorante;

    private Coupon coupon;

    private List<Recensione> recensioneList;

    private CouponService couponService;

    private PrenotazioneService prenotazioneService;

    private RecensioniService recensioniService;

    private CouponBusinessFactory factory;

    public prenotaRistoranteController() {
        factory = CouponBusinessFactory.getInstance();
        couponService = factory.getCouponService();
        prenotazioneService = factory.getPrenotazioneService();
        recensioniService = factory.getRecensioniService();
        dispatcher = ViewDispatcher.getInstance();
    }

    @FXML
    void prenotaAction(ActionEvent event) {
        Prenotazione prenotazione = new Prenotazione();

        try {
            prenotazione.setRistorante(coupon.getRistorante());
            prenotazione.setCliente(cliente);
            prenotazione.setCoupon(coupon);
            prenotazione.setStato(Stato.DA_VERIFICARE);

            prenotazione.setNumeroPersone(Integer.parseInt(numeroPersonePrenotazioneTextField.getText()));
            prenotazione.setLocalDate(dataPrenotazionePicker.getValue());
            prenotazione.setLocalTime(LocalTime.parse(oreTextField.getText() + ":" + minutiTextField.getText()));

            prenotazioneService.creaPrenotazione(prenotazione);
            dispatcher.renderView("prenotazioniCliente", cliente);

        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void voglioPrenotareAction(ActionEvent event) {
        numeroPersonePrenotazioneTextField.setVisible(true);
        dataPrenotazionePicker.setVisible(true);
        oreTextField.setVisible(true);
        minutiTextField.setVisible(true);
        prenotaButton.setVisible(true);

        text1.setVisible(true);
        text2.setVisible(true);
        text3.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dispatcher.renderView("allCoupon", cliente);
            }
        });


        nomeCouponTextField.setEditable(false);
        nomeRistoranteTextField.setEditable(false);
        indirizzoRistoranteTextField.setEditable(false);
        numeroCivicoRistoranteTextField.setEditable(false);
        valoreScontoCouponTextField.setEditable(false);
        descrizioneCouponArea.setEditable(false);


        numeroPersonePrenotazioneTextField.setVisible(false);
        dataPrenotazionePicker.setVisible(false);
        oreTextField.setVisible(false);
        minutiTextField.setVisible(false);
        prenotaButton.setVisible(false);

        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);

        recensioniListView.setCellFactory(new Callback<ListView<Recensione>, ListCell<Recensione>>() {
            @Override
            public ListCell<Recensione> call(ListView<Recensione> param) {
                ListCell<Recensione> cell = new ListCell<Recensione>() {
                    @Override
                    protected void updateItem(Recensione item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getTesto());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

    }

    @Override
    public void initializeData(Coupon coupon) {
        this.coupon = coupon;
        this.ristorante = coupon.getRistorante();

        this.cliente = coupon.getCliente();

        nomeCouponTextField.setText(coupon.getNome());
        nomeRistoranteTextField.setText(coupon.getRistorante().getNome());
        indirizzoRistoranteTextField.setText(coupon.getRistorante().getIndirizzo());
        numeroCivicoRistoranteTextField.setText(coupon.getRistorante().getNumeroCivico());
        valoreScontoCouponTextField.setText(String.valueOf(coupon.getSconto()));

        descrizioneCouponArea.setText(coupon.getDescrizione());

        //QUA CI DEVO METTERE LE RECENSIONI

        try {
            recensioneList = recensioniService.cercaRecensioniPerRistorante(ristorante);
            ObservableList recensioniData = FXCollections.observableArrayList(recensioneList);
            recensioniListView.setItems(recensioniData);
        } catch (BusinessException e) {
            e.printStackTrace();
        }


    }
}
