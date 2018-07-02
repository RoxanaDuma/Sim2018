package it.polito.tdp.flightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.flightdelays.db.FlightDelaysDAO;

public class Model {
	

	private FlightDelaysDAO dao ;
	private List<Airline> allAirlines ;
	private List<Airport> allAirports;
	private List<Airport> airportByAirline;
	private Map<Integer,Airport> airportIdMap;
	private SimpleDirectedWeightedGraph <Airport,DefaultWeightedEdge> grafo ;
	
	public Model() {
		
		this.dao = new FlightDelaysDAO();
		this.airportIdMap = new HashMap<>();
		this.allAirports = dao.loadAllAirports(airportIdMap);
		
		
	}
	
	
	public Airport getAirport(int airportId) {
		
		return this.airportIdMap.get(airportId);
		
	}
	
	
	public List<Airline> getAllAirlines(){
		
		this.allAirlines = dao.loadAllAirlines();
		Collections.sort(allAirlines);
		return this.allAirlines;
		
	}
	
	
	public void creaGrafo(Airline airline) {
		
		this.dao = new FlightDelaysDAO();
		this.grafo = new SimpleDirectedWeightedGraph<Airport,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		this.airportByAirline = new ArrayList<>();
		airportByAirline = dao.getAirportsByAirline(airline,airportIdMap);
		
		Graphs.addAllVertices(this.grafo, this.airportByAirline);
		
		
		List<CoppiaAirports> archi = dao.getCoppiaAirports(airline);
		
		for(CoppiaAirports ca : archi ) {
			
			Airport a1 = this.airportIdMap.get(ca.getAirportId1());
			Airport a2 = this.airportIdMap.get(ca.getAirportId2());
			
			if( a1!=null && a2!= null && !a1.equals(a2) ) {
				
				double distanza = this.calcolaDistanza(a1,a2);
				
				double mediaRitardi = dao.getMediaRitardi(a1,a2,airline);
				
				if(distanza!=0.0 && mediaRitardi!=0.0) {
					
					double peso = mediaRitardi / distanza ;
					
					Graphs.addEdge(this.grafo, a1, a2, peso);
					
				
				}
				
				}
			}
			
		
		
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
		
	}


	private double calcolaDistanza(Airport a1, Airport a2) {
		
		if( (a1.getLatitude()!=0.0 || a1.getLongitude()!=0.0) || ( a2.getLatitude()!=0.0 || a2.getLongitude()!=0.0)) {
		
		double distanza = LatLngTool.distance(new LatLng(a1.getLatitude(), 
				a1.getLongitude()), new LatLng(a2.getLatitude(), 
					a2.getLongitude()), LengthUnit.KILOMETER);
		
		
		return distanza;
		}
		
		return 0.0;
	}
	
	
	public List<DefaultWeightedEdge> getArchiPesoMax(){
		
		Set<DefaultWeightedEdge> archi = this.grafo.edgeSet();
		List<DefaultWeightedEdge> res = new ArrayList<>();
		//List<DefaultWeightedEdge> res = new ArrayList<>(archi);
		List<DefaultWeightedEdge> result = new ArrayList<>();
		
		for( DefaultWeightedEdge dwe : archi ) {
			
			res.add(dwe);
			
		}
		
		res.sort(new Comparator <DefaultWeightedEdge>() {
			
			@Override
			public int compare(DefaultWeightedEdge a1, DefaultWeightedEdge a2) {
				return -(Double.compare(grafo.getEdgeWeight(a1), grafo.getEdgeWeight(a2))); 
				
			}
			
		});
		
		for(int i=0; i<10;i++) {
			result.add(res.get(i));
		}
		
		return result;
		
	}

	
/*	public List<AirportAndWeight> worstF(){
		
		AirportAndWeight a = null;
		
		List<AirportAndWeight> in = new ArrayList<>();
		List<AirportAndWeight> out = new ArrayList<>();
		
		for(DefaultWeightedEdge dwe : this.grafo.edgeSet() ) {
			
			Airport o = grafo.getEdgeSource(dwe);
			Airport d = grafo.getEdgeTarget(dwe);
			
			double peso = grafo.getEdgeWeight(dwe);
			
			a = new AirportAndWeight(o,d,peso);
			
			in.add(a);
		}
		
		Collections.sort(in);
		
		for(int i =0 ; i < 10 ; i++ ) {
			out.add(in.get(i));
		}
		
		return out;
		
	}
	
	*/
	
	

	public double getPesoArco(DefaultWeightedEdge dwe) {
		
		if(grafo!=null) {
			return this.grafo.getEdgeWeight(dwe);
			}
			return 0.0;
		
	}
	
}
