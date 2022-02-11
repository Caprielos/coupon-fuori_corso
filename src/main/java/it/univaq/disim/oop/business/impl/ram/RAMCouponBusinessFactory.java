package it.univaq.disim.oop.business.impl.ram;

import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.CouponService;
import it.univaq.disim.oop.business.service.UtenteService;

public class RAMCouponBusinessFactory extends CouponBusinessFactory {

    private UtenteService utenteService;

    private CouponService couponService;

    public RAMCouponBusinessFactory() {
        utenteService = new RAMUtenteService();
        couponService = new RAMCouponService();
    }

    @Override
    public UtenteService getUtenteService() {
        return utenteService;
    }

    @Override
    public CouponService getCouponService() {
        return couponService;
    }
}
