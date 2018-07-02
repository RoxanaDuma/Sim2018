package it.polito.tdp.flightdelays.model;

public class Airport {

	private int idAirport;
	private String iataCode;
	private String name;
	private String city;
	private String state;
	private String country;
	private double latitude;
	private double longitude;
	private double timezone;
	
	
	

	public Airport(int idAirport, String iataCode, String name, String city, String state, String country,
			double latitude, double longitude, double timezone) {
		super();
		this.idAirport = idAirport;
		this.iataCode = iataCode;
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timezone = timezone;
	}


	

	public int getIdAirport() {
		return idAirport;
	}




	public void setIdAirport(int idAirport) {
		this.idAirport = idAirport;
	}




	public String getIataCode() {
		return iataCode;
	}




	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public String getCountry() {
		return country;
	}




	public void setCountry(String country) {
		this.country = country;
	}




	public double getLatitude() {
		return latitude;
	}




	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}




	public double getLongitude() {
		return longitude;
	}




	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}




	public double getTimezone() {
		return timezone;
	}




	public void setTimezone(double timezone) {
		this.timezone = timezone;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		//builder.append("Airport [name=");
		builder.append(name+"\n");
	//	builder.append(this.latitude);
		//builder.append(this.longitude);
		//builder.append("]");
		return builder.toString();
	}
	
}
