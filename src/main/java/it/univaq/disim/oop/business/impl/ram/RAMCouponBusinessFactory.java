package it.univaq.disim.oop.business.impl.ram;

import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.UtenteService;

public class RAMCouponBusinessFactory extends CouponBusinessFactory {

    private UtenteService utenteService;

    public RAMCouponBusinessFactory() {
        utenteService = new RAMUtenteService();
    }

    @Override
    public UtenteService getUtenteService() {
        return utenteService;
    }
}
