package it.univaq.disim.oop.business.impl.ram;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.service.PrenotazioneService;
import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Prenotazione;
import it.univaq.disim.oop.domain.Ristorante;
import it.univaq.disim.oop.domain.Stato;

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

    @Override
    public List<Prenotazione> cercaPrenotazioniPerCliente(Cliente cliente) throws BusinessException {
        List<Prenotazione> result = new ArrayList<>();

        for (Prenotazione prenotazione : prenotazioniList) {
            if (cliente.getEmail().equalsIgnoreCase(prenotazione.getCliente().getEmail())) {
                result.add(prenotazione);
            }
        }
        return result;
    }

    @Override
    public List<Prenotazione> cercaPrenotazioniPerRistorante(Ristorante ristorante) throws BusinessException {
        List<Prenotazione> result = new ArrayList<>();

        for (Prenotazione prenotazione : prenotazioniList) {
            if (prenotazione.getStato().equals(Stato.DA_VERIFICARE) && prenotazione.getRistorante().getEmail().equalsIgnoreCase(ristorante.getEmail())) {
                result.add(prenotazione);
            }
        }
        return result;


    }


}
