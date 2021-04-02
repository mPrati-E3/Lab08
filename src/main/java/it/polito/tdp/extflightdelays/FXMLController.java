/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    //funzione prinicpale che si attiva quando premo il pulsante
    //se l'input è accettabile, chiama la funzione stampante che stamperà ciò che è restituito dal model
    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	
    	Double MigliaUtente;
    	
    	try {
    		MigliaUtente=Double.parseDouble(distanzaMinima.getText());
    	} catch (NumberFormatException e) {
    		txtResult.appendText("La distanza minima deve essere numerica! \n");
    		return;
    	}
    	
    	this.Stampante(this.model.analizzaAeroporti(MigliaUtente));
    	
	
    }

    //stampa un grafo nella text area
    private void Stampante(Graph<Airport, DefaultWeightedEdge> tratte) {
    	
		txtResult.clear();
		txtResult.appendText("Numero di vertici: "+tratte.vertexSet().size()+"\n");
		txtResult.appendText("Numero di archi: "+tratte.edgeSet().size()+"\n\n\n");
	
		for (DefaultWeightedEdge dwe : tratte.edgeSet()) {
			txtResult.appendText(tratte.getEdgeSource(dwe).getAirportName()+" -> ");
			txtResult.appendText(tratte.getEdgeTarget(dwe).getAirportName()+" - Peso: ");
			txtResult.appendText(tratte.getEdgeWeight(dwe)+"\n");
		}
		
		
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
