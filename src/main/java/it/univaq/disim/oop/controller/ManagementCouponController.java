package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.CouponService;
import it.univaq.disim.oop.domain.Coupon;
import it.univaq.disim.oop.domain.Ristorante;
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
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManagementCouponController implements Initializable, DataInitializable<Ristorante> {

    @FXML
    private TableView<Coupon> couponTableView;

    @FXML
    private TableColumn<Coupon, Button> cancellaButtonTableColumn;

    @FXML
    private TableColumn<Coupon, String> codeCouponTableColumn;

    @FXML
    private TableColumn<Coupon, String> dataFineCouponTableColumn;

    @FXML
    private TableColumn<Coupon, String> dataInizioCouponTableColumn;

    @FXML
    private TableColumn<Coupon, String> disponibilitàCouponTableColumn;

    @FXML
    private TableColumn<Coupon, String> nomeCouponTableColumn;

    @FXML
    private TableColumn<Coupon, Integer> scontoCouponTableColumn;

    @FXML
    private TableColumn<Coupon, Integer> quantitaCouponTableColumn;

    @FXML
    private TableColumn<Coupon, Button> infoButtonTableColumn;

    @FXML
    private TextField codiceCouponTextFiled;

    @FXML
    private Button creaCouponButton;

    @FXML
    private DatePicker dataFineCouponTextFiled;

    @FXML
    private DatePicker dataInizioCouponTextFiled;

    @FXML
    private TextField nomeCouponTextFiled;

    @FXML
    private TextField quantitaCouponTextFiled;

    @FXML
    private TextField scontoCouponTextFiled;

    @FXML
    private TextArea descrizioneCouponArea;

    private CouponService couponService;

    private CouponBusinessFactory factory;

    private ViewDispatcher dispatcher;

    private Coupon coupon;

    private List<Coupon> couponList;

    private Ristorante ristorante;

    public ManagementCouponController() {
        factory = CouponBusinessFactory.getInstance();
        couponService = factory.getCouponService();
        dispatcher = ViewDispatcher.getInstance();
    }


    @FXML
    void creaCoupon(ActionEvent event) {
        try {
            Coupon coupon = new Coupon();
            coupon.setNome(nomeCouponTextFiled.getText());
            coupon.setCodice(codiceCouponTextFiled.getText());
            coupon.setSconto(Integer.parseInt(scontoCouponTextFiled.getText()));
            coupon.setDataInizio(dataInizioCouponTextFiled.getValue());
            coupon.setDataFine(dataFineCouponTextFiled.getValue());
            coupon.setQuantita(Integer.parseInt(quantitaCouponTextFiled.getText()));
            coupon.setRistorante(ristorante);
            coupon.setDescrizione(descrizioneCouponArea.getText());

            couponService.creaCoupon(coupon);
            dispatcher.renderView("managementCoupon", ristorante);

        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cancellaButtonTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, Button>, ObservableValue<Button>>() {
            @Override
            public ObservableValue<Button> call(TableColumn.CellDataFeatures<Coupon, Button> couponButtonCellDataFeatures) {
                Button deleteButton = new Button("Cancella");
                deleteButton.setStyle("-fx-background-color:red; -fx-background-radius: 30; -fx-border-radius: 60 ; -fx-border-color: white; -fx-text-fill: white ");
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            couponService.cancellaCoupon(couponButtonCellDataFeatures.getValue());
                        } catch (BusinessException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return new SimpleObjectProperty<Button>(deleteButton);
            }
        });

        infoButtonTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, Button>, ObservableValue<Button>>() {
            @Override
            public ObservableValue<Button> call(TableColumn.CellDataFeatures<Coupon, Button> couponButtonCellDataFeatures) {
                Button infoButton = new Button("Info");
                infoButton.setStyle("-fx-background-color:green; -fx-background-radius: 30; -fx-border-radius: 60 ; -fx-border-color: white; -fx-text-fill: white ");
                infoButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        dispatcher.renderView("descrizioneCoupon", couponButtonCellDataFeatures.getValue());
                    }
                });
                return new SimpleObjectProperty<Button>(infoButton);
            }
        });


        codeCouponTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Coupon, String> couponStringCellDataFeatures) {
                return new SimpleStringProperty(couponStringCellDataFeatures.getValue().getCodice());
            }
        });

        nomeCouponTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Coupon, String> couponStringCellDataFeatures) {
                return new SimpleStringProperty(couponStringCellDataFeatures.getValue().getNome());
            }
        });

        scontoCouponTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Coupon, Integer> couponIntegerCellDataFeatures) {
                return new SimpleObjectProperty<>(couponIntegerCellDataFeatures.getValue().getSconto());
            }
        });

        quantitaCouponTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Coupon, Integer> couponIntegerCellDataFeatures) {
                return new SimpleObjectProperty<>(couponIntegerCellDataFeatures.getValue().getQuantita());
            }
        });

    }

    @Override
    public void initializeData(Ristorante ristorante) {
        this.ristorante = ristorante;

        try {
            couponList = couponService.cercaCouponPerRistorante(ristorante);
            ObservableList couponListData = FXCollections.observableArrayList(couponList);
            couponTableView.setItems(couponListData);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}

