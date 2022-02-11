package it.univaq.disim.oop.business;

import it.univaq.disim.oop.business.impl.ram.RAMCouponBusinessFactory;
import it.univaq.disim.oop.business.service.CouponService;
import it.univaq.disim.oop.business.service.UtenteService;

public abstract class CouponBusinessFactory {

    private static CouponBusinessFactory factory = new RAMCouponBusinessFactory();

    public static CouponBusinessFactory getInstance() {
        return factory;
    }

    public abstract UtenteService getUtenteService();

    public abstract CouponService getCouponService();
}
