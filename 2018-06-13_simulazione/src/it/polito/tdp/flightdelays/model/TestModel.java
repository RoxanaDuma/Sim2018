package it.polito.tdp.flightdelays.model;


public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		m.creaGrafo(new Airline(13,"VX","Virgin America"));
		
	//	System.out.println(m.worstF());
		
		System.out.println(m.getArchiPesoMax());
		
		//System.out.println(m.getAirport(313));
	}

}
