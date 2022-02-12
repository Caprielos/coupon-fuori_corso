package it.univaq.disim.oop.business.impl.ram;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.service.RecensioniService;
import it.univaq.disim.oop.domain.Recensione;

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


}
