package it.univaq.disim.oop.business.impl.ram;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.service.PrenotazioneService;
import it.univaq.disim.oop.domain.Prenotazione;

import java.util.ArrayList;
import java.util.List;

public class RAMPrenotazioneService implements PrenotazioneService {

    final static List<Prenotazione> prenotazioniList = new ArrayList<>();
    private static Integer id = 1;

    @Override
    public void creaPrenotazione(Prenotazione prenotazione) throws BusinessException {
        prenotazione.setId(++id);
        prenotazioniList.add(prenotazione);
    }
}
