package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> graph;
	private List<Airport> ListAirport;
	private Map<Integer, Airport> MapAirport;
	private List<Flight> ListFlight;
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		
		this.dao=new ExtFlightDelaysDAO();
		
		this.graph = new DirectedWeightedMultigraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//creazione vertici del grafo base
		this.ListAirport=dao.loadAllAirports();
		this.MapAirport=new HashMap<Integer, Airport>();
		for (Airport a : ListAirport) {
			graph.addVertex(a);
			this.MapAirport.put(a.getId(), a);
		}
		
		//creazione archi del grafo base
		this.ListFlight=dao.loadAllFlights();
		for (Flight f : ListFlight) {
			Graphs.addEdge(
					graph, 
					MapAirport.get(f.getOriginAirportId()), 
					MapAirport.get(f.getDestinationAirportId()), 
					f.getDistance()); 
		}
		
		System.out.println("Grafo base creato: V="+graph.vertexSet().size()+" E="+graph.edgeSet().size());
		
	}

	public Graph<Airport, DefaultWeightedEdge> analizzaAeroporti(int migliaUtente) {
		
		Graph<Airport, DefaultWeightedEdge> UserGraph = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//genero i vertici del grafo utente
		for (Airport a : ListAirport) {
			UserGraph.addVertex(a);
		}
		
		for (DefaultWeightedEdge dwe : graph.edgeSet()) {
			
			
			/*
			Graphs.addEdge(
					UserGraph, 
					MapAirport.get(graph.getEdgeSource(NewEdge)), 
					MapAirport.get(f.getDestinationAirportId()), 
					f.getDistance());
			}*/
		}
		
		//secondo me conviene creare un graph che ha tutto, poi creare un grafo vuoto e popolarlo con i PATH
		// che vanno bene ovvero che hanno la media delle DISTANCE < migliaUtente
		
		return null;
	}
	
	

}
