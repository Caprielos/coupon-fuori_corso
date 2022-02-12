package it.univaq.disim.oop.controller;

import it.univaq.disim.oop.business.BusinessException;
import it.univaq.disim.oop.business.CouponBusinessFactory;
import it.univaq.disim.oop.business.service.RecensioniService;
import it.univaq.disim.oop.domain.Cliente;
import it.univaq.disim.oop.domain.Recensione;
import it.univaq.disim.oop.domain.Ristorante;
import it.univaq.disim.oop.domain.Utente;
import it.univaq.disim.oop.view.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecensioniController implements Initializable, DataInitializable<Utente> {

    @FXML
    private ListView<Recensione> recensioniListView;

    @FXML
    private Text Text1;

    @FXML
    private Button inviaRecensioneButton;

    @FXML
    private TextArea recenisioneAreaText;


    private ViewDispatcher dispatcher;

    private CouponBusinessFactory factory;

    private RecensioniService recensioniService;

    private Cliente cliente;

    private Ristorante ristorante;

    private List<Recensione> recensioneList;


    public RecensioniController() {
        factory = CouponBusinessFactory.getInstance();
        recensioniService = factory.getRecensioniService();
        dispatcher = ViewDispatcher.getInstance();
    }

    @FXML
    void inviaRecensioneAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        recensioniListView.setCellFactory(new Callback<ListView<Recensione>, ListCell<Recensione>>() {
            @Override
            public ListCell<Recensione> call(ListView<Recensione> param) {
                ListCell<Recensione> cell = new ListCell<Recensione>() {
                    @Override
                    protected void updateItem(Recensione item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getTesto());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });


    }

    @Override
    public void initializeData(Utente utente) {
        try {
            if (utente instanceof Cliente) {
                this.cliente = (Cliente) utente;
                recensioneList = recensioniService.cercaRecensioniPerCliente(cliente);

            } else if (utente instanceof Ristorante) {
                this.ristorante = (Ristorante) utente;
                recensioneList = recensioniService.cercaRecensioniPerRistorante(ristorante);
            }

            ObservableList recensioniData = FXCollections.observableArrayList(recensioneList);
            recensioniListView.setItems(recensioniData);

        } catch (BusinessException e) {
            e.printStackTrace();
        }


    }


}

