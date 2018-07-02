package it.polito.tdp.flightdelays;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;
import it.polito.tdp.flightdelays.model.Airline;
import it.polito.tdp.flightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FlightDelaysController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private ComboBox<Airline> cmbBoxLineaAerea;

    @FXML
    private Button caricaVoliBtn;

    @FXML
    private TextField numeroPasseggeriTxtInput;

    @FXML
    private TextField numeroVoliTxtInput;

    @FXML
    void doCaricaVoli(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	Airline airline = cmbBoxLineaAerea.getValue();
    	
    	if( airline == null) {
    		txtResult.appendText("Seleziona una compagnia aerea.\n");
    		return;
    	}
    	
    	try {
    	
    	model.creaGrafo(airline);
    	List<DefaultWeightedEdge>archi = model.getArchiPesoMax();
    	
    	for(DefaultWeightedEdge dwe : archi ) {
    		txtResult.appendText("Arco : "+dwe+" con peso : "+model.getPesoArco(dwe)+"\n");
    		//txtResult.appendText("Arco : ("+model.getGrafo().getEdgeSource(dwe)+"-"+model.getGrafo().getEdgeTarget(dwe)+")"+" con peso : "+model.getPesoArco(dwe)+"\n");
    	}
    	}catch(RuntimeException re) {
    		txtResult.appendText("Erroe connessione al DB o query errata");
    		return;
    	}
    	
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    		System.out.println("Simula!");
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert cmbBoxLineaAerea != null : "fx:id=\"cmbBoxLineaAerea\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert caricaVoliBtn != null : "fx:id=\"caricaVoliBtn\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert numeroPasseggeriTxtInput != null : "fx:id=\"numeroPasseggeriTxtInput\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert numeroVoliTxtInput != null : "fx:id=\"numeroVoliTxtInput\" was not injected: check your FXML file 'FlightDelays.fxml'.";

    }
    
	public void setModel(Model model) {
		this.model = model ;
		
		try {
			cmbBoxLineaAerea.getItems().addAll(model.getAllAirlines());
		}catch(RuntimeException re ) {
			txtResult.appendText("Errore connessione .\n");
		}
	
	}
}
