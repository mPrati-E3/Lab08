package it.polito.tdp.extflightdelays.model;

import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> graph;
	private Map<Integer, Airport> MapAirport;
	private List<Flight> ListFlight;
	private ExtFlightDelaysDAO dao;

	public Graph<Airport, DefaultWeightedEdge> analizzaAeroporti(int migliaUtente) {
		
		this.graph = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		this.MapAirport=dao.getDBAirport();
		this.ListFlight=dao.getDBFlight(migliaUtente);
		
		//secondo me conviene creare un graph che ha tutto, poi creare un grafo vuoto e popolarlo con i PATH
		// che vanno bene ovvero che hanno la media delle DISTANCE < migliaUtente
		
		return null;
	}
	
	

}
