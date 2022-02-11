package it.univaq.disim.oop.business.service;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.domain.Prenotazione;

public interface PrenotazioneService {

    void creaPrenotazione(Prenotazione prenotazione) throws BusinessException;
}
