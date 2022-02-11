package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.domain.Coupon;
import it.univaq.disim.oop.domain.Utente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initializeData(Utente utente) {
    }
}
