package it.polito.tdp.extflightdelays.model;

//javabean di servizio per salvarsi le informazioni sul nuovo edge (preso dal db)
//che verrà poi inserito nel grafo richiesto dall'utente
public class BoxEdge {
	
	private int VerticeA;
	private int VerticeB;
	private Double MediaDistanza;
	
	public int getVerticeA() {
		return VerticeA;
	}
	public void setVerticeA(int verticeA) {
		VerticeA = verticeA;
	}
	public int getVerticeB() {
		return VerticeB;
	}
	public void setVerticeB(int verticeB) {
		VerticeB = verticeB;
	}
	public Double getMediaDistanza() {
		return MediaDistanza;
	}
	public void setMediaDistanza(Double mediaDistanza) {
		MediaDistanza = mediaDistanza;
	}
	
	public BoxEdge(int verticeA, int verticeB, Double mediaDistanza) {
		super();
		VerticeA = verticeA;
		VerticeB = verticeB;
		MediaDistanza = mediaDistanza;
	}
	
	public BoxEdge() {
		super();
		VerticeA = 0;
		VerticeB = 0;
		MediaDistanza = 0.0;
	}
	
	
	
	

}
