package it.univaq.disim.oop.business.service;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.domain.Coupon;
import it.univaq.disim.oop.domain.Ristorante;

import java.util.List;

public interface CouponService {

    List<Coupon> cercaCouponPerRistorante(Ristorante ristorante) throws BusinessException;

    void creaCoupon(Coupon coupon) throws BusinessException;

    void cancellaCoupon(Coupon coupon) throws BusinessException;

    List<Coupon> cercaAllCoupon() throws BusinessException;
}
