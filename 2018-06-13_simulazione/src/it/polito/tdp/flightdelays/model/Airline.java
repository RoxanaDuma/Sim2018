package it.polito.tdp.flightdelays.model;

public class Airline implements Comparable<Airline> {
	
	private int idAirline;
	private String iata;
	private String name;
	
	

	public Airline(int idAirline, String iata, String name) {
		super();
		this.idAirline = idAirline;
		this.iata = iata;
		this.name = name;
	}



	public int getIdAirline() {
		return idAirline;
	}



	public void setIdAirline(int idAirline) {
		this.idAirline = idAirline;
	}



	public String getIata() {
		return iata;
	}



	public void setIata(String iata) {
		this.iata = iata;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return this.name;
	}
	
	
	@Override
	public int compareTo(Airline other) {
		
		return this.name.compareTo(other.getName());
	}
}
