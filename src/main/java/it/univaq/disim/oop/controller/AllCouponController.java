package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.CouponService;
import it.univaq.disim.oop.domain.Coupon;
import it.univaq.disim.oop.domain.Ristorante;
import it.univaq.disim.oop.domain.Utente;
import it.univaq.disim.oop.view.ViewDispatcher;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AllCouponController implements Initializable, DataInitializable<Utente> {

    @FXML
    private TableView<Coupon> couponTableView;

    @FXML
    private TableColumn<Coupon, Button> infoButtonTableColumn;

    @FXML
    private TableColumn<Coupon, String> nomeCouponTableColumn;

    @FXML
    private TableColumn<Coupon, String> nomeRistoranteTableColumn;

    @FXML
    private TableColumn<Coupon, String> scontoTableColumn;

    private CouponService couponService;

    private CouponBusinessFactory factory;

    private ViewDispatcher dispatcher;

    private Coupon coupon;

    private List<Coupon> couponList;

    private Ristorante ristorante;

    public AllCouponController() {
        factory = CouponBusinessFactory.getInstance();
        couponService = factory.getCouponService();
        dispatcher = ViewDispatcher.getInstance();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nomeCouponTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Coupon, String> couponStringCellDataFeatures) {
                return new SimpleStringProperty(couponStringCellDataFeatures.getValue().getNome());
            }
        });

    }

    @Override
    public void initializeData(Utente utente) {
        try {
            couponList = couponService.cercaAllCoupon();
            ObservableList couponListData = FXCollections.observableArrayList(couponList);
            couponTableView.setItems(couponListData);


        } catch (BusinessException e) {
            e.printStackTrace();
        }


    }
}
