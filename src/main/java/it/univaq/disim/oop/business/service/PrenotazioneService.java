package it.univaq.disim.oop.business.service;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Coupon;
import it.univaq.disim.oop.domain.Prenotazione;
import it.univaq.disim.oop.domain.Ristorante;

import java.util.List;

public interface PrenotazioneService {

    void creaPrenotazione(Prenotazione prenotazione) throws BusinessException;

    List<Prenotazione> cercaPrenotazioniPerCliente(Cliente cliente) throws BusinessException;
}
