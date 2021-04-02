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
	
	//dischiaro grafo, aeroporti, voli e dao
	private Graph<Airport, DefaultWeightedEdge> graph;
	private List<Airport> ListAirport;
	private Map<Integer, Airport> MapAirport;
	private List<Flight> ListFlight;
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		
		//definisco il dao
		this.dao=new ExtFlightDelaysDAO();
		
		//definisco il grafo di base
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
		
	}

	//funzione principale: date le miglia utente da controller, genero un nuovo grafo che abbia come
	//nodi gli stessi di quello base ma gli archi sono non direzionali e hanno come peso la media delle
	//distanze tra due nodi di quello base
	public Graph<Airport, DefaultWeightedEdge> analizzaAeroporti(Double migliaUtente) {
		
		//dichiaro e definisco il grafo che verrà poi ritornato (vuoto)
		Graph<Airport, DefaultWeightedEdge> UserGraph = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//genero i vertici del grafo utente
		for (Airport a : ListAirport) {
			UserGraph.addVertex(a);
		}
		
		//genero gli archi
		for (DefaultWeightedEdge dwe : graph.edgeSet()) {
			
			BoxEdge BOX = dao.generateNewEdge(graph.getEdgeSource(dwe).getId(), graph.getEdgeTarget(dwe).getId());
			
			if (BOX.getMediaDistanza()>=migliaUtente) {
				Graphs.addEdge(
					UserGraph, 
					MapAirport.get(BOX.getVerticeA()), 
					MapAirport.get(BOX.getVerticeB()), 
					BOX.getMediaDistanza());
			}
			
		}
		
		
		return UserGraph;
	}
	
	

}
