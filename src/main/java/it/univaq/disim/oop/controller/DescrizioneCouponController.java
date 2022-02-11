package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.CouponService;
import it.univaq.disim.oop.domain.Coupon;
import it.univaq.disim.oop.view.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DescrizioneCouponController implements Initializable, DataInitializable<Coupon> {


    @FXML
    private TextField nomeCouponTextField;

    @FXML
    private TextArea descrzioneTextArea;

    private CouponBusinessFactory factory;

    private CouponService couponService;

    private ViewDispatcher dispatcher;

    private Coupon coupon;

    public DescrizioneCouponController() {
        factory = CouponBusinessFactory.getInstance();
        couponService = factory.getCouponService();
        dispatcher = ViewDispatcher.getInstance();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initializeData(Coupon coupon) {
        this.coupon = coupon;

        this.nomeCouponTextField.setText(coupon.getNome());
        this.descrzioneTextArea.setText(coupon.getDescrizione());
    }
}
