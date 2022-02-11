package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.CouponService;
import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Coupon;
import it.univaq.disim.oop.domain.Recensione;
import it.univaq.disim.oop.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
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

    private Coupon coupon;

    private CouponService couponService;

    private CouponBusinessFactory factory;

    public prenotaRistoranteController() {
        factory = CouponBusinessFactory.getInstance();
        couponService = factory.getCouponService();
        dispatcher = ViewDispatcher.getInstance();
    }

    @FXML
    void prenotaAction(ActionEvent event) {




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

        numeroPersonePrenotazioneTextField.setVisible(false);
        dataPrenotazionePicker.setVisible(false);
        oreTextField.setVisible(false);
        minutiTextField.setVisible(false);
        prenotaButton.setVisible(false);

        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);

    }

    @Override
    public void initializeData(Coupon coupon) {
        this.coupon = coupon;

        this.cliente = coupon.getCliente();

        nomeCouponTextField.setText(coupon.getNome());
        nomeRistoranteTextField.setText(coupon.getRistorante().getNome());
        indirizzoRistoranteTextField.setText(coupon.getRistorante().getIndirizzo());
        numeroCivicoRistoranteTextField.setText(coupon.getRistorante().getNumeroCivico());
        valoreScontoCouponTextField.setText(String.valueOf(coupon.getSconto()));

        descrizioneCouponArea.setText(coupon.getDescrizione());

        //QUA CI DEVO METTERE LE RECENSIONI




    }
}
