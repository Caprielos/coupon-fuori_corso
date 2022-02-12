package it.univaq.disim.oop.business.impl.ram;

import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.CouponService;
import it.univaq.disim.oop.business.service.PrenotazioneService;
import it.univaq.disim.oop.business.service.RecensioniService;
import it.univaq.disim.oop.business.service.UtenteService;

public class RAMCouponBusinessFactory extends CouponBusinessFactory {

    private UtenteService utenteService;

    private CouponService couponService;

    private PrenotazioneService prenotazioneService;

    private RecensioniService recensioniService;

    public RAMCouponBusinessFactory() {
        utenteService = new RAMUtenteService();
        couponService = new RAMCouponService();
        prenotazioneService = new RAMPrenotazioneService();
        recensioniService = new RAMRecensioniService();
    }

    @Override
    public UtenteService getUtenteService() {
        return utenteService;
    }

    @Override
    public CouponService getCouponService() {
        return couponService;
    }

    @Override
    public PrenotazioneService getPrenotazioneService() {
        return prenotazioneService;
    }

    @Override
    public RecensioniService getRecensioniService() {
        return recensioniService;
    }


}
