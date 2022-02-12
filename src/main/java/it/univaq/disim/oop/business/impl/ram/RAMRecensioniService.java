package it.univaq.disim.oop.business.impl.ram;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.service.RecensioniService;
import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Recensione;
import it.univaq.disim.oop.domain.Ristorante;

import java.util.ArrayList;
import java.util.List;

public class RAMRecensioniService implements RecensioniService {

    final static List<Recensione> recensioneList = new ArrayList<>();
    private static Integer id = 1;


    @Override
    public void creaRecensione(Recensione recensione) throws BusinessException {
        recensione.setId(++id);
        recensioneList.add(recensione);
    }

    @Override
    public List<Recensione> cercaRecensioniPerRistorante(Ristorante ristorante) throws BusinessException {
        List<Recensione> result = new ArrayList<>();

        for (Recensione recensione : recensioneList) {
            if (recensione.getRistorante().getEmail().equalsIgnoreCase(ristorante.getEmail())) {
                result.add(recensione);
            }
        }

        return result;
    }

    @Override
    public List<Recensione> cercaRecensioniPerCliente(Cliente cliente) throws BusinessException {
        List<Recensione> result = new ArrayList<>();

        for (Recensione recensione : recensioneList) {
            if (recensione.getCliente().getEmail().equalsIgnoreCase(cliente.getEmail())) {
                result.add(recensione);
            }
        }

        return result;

    }


}
