package it.univaq.disim.oop.business.impl.ram;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.service.CouponService;
import it.univaq.disim.oop.domain.Coupon;
import it.univaq.disim.oop.domain.Ristorante;

import java.util.ArrayList;
import java.util.List;

public class RAMCouponService implements CouponService {

    final static List<Coupon> magazine = new ArrayList<>();
    private static Integer id = 1;


    @Override
    public List<Coupon> cercaCouponPerRistorante(Ristorante ristorante) throws BusinessException {
        List<Coupon> result = new ArrayList<>();
        for (Coupon coupon : magazine) {
            if (coupon.getRistorante().getId() == ristorante.getId()) {
                result.add(coupon);
            }
        }
        return result;
    }

    @Override
    public void creaCoupon(Coupon coupon) throws BusinessException {
        coupon.setId(++id);
        magazine.add(coupon);
    }

    @Override
    public void cancellaCoupon(Coupon coupon) throws BusinessException {
        magazine.remove(coupon);
    }


}
