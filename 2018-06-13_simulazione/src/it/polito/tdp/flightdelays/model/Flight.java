package it.polito.tdp.flightdelays.model;

import java.time.LocalDateTime;

public class Flight {

	private int id;
	private int airlineId;
	private int flightNumber;
	private String tailNumber;
	private int originAirportId;
	private int destinationAirportId;
	private LocalDateTime scheduledDepartureDate;
	private double departureDelay;
	private double elapsedTime;
	private LocalDateTime arrivalDate;
	private int distance;
	private double arrivalDelay;
	
	public Flight(int id, int airlineId, int flightNumber, String tailNumber, int originAirportId,
			int destinationAirportId, LocalDateTime scheduledDepartureDate, double departureDelay, double elapsedTime,
			LocalDateTime arrivalDate, int distance, double arrivalDelay) {
		super();
		this.id = id;
		this.airlineId = airlineId;
		this.flightNumber = flightNumber;
		this.tailNumber = tailNumber;
		this.originAirportId = originAirportId;
		this.destinationAirportId = destinationAirportId;
		this.scheduledDepartureDate = scheduledDepartureDate;
		this.departureDelay = departureDelay;
		this.elapsedTime = elapsedTime;
		this.arrivalDate = arrivalDate;
		this.distance = distance;
		this.arrivalDelay = arrivalDelay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getTailNumber() {
		return tailNumber;
	}

	public void setTailNumber(String tailNumber) {
		this.tailNumber = tailNumber;
	}

	public int getOriginAirportId() {
		return originAirportId;
	}

	public void setOriginAirportId(int originAirportId) {
		this.originAirportId = originAirportId;
	}

	public int getDestinationAirportId() {
		return destinationAirportId;
	}

	public void setDestinationAirportId(int destinationAirportId) {
		this.destinationAirportId = destinationAirportId;
	}

	public LocalDateTime getScheduledDepartureDate() {
		return scheduledDepartureDate;
	}

	public void setScheduledDepartureDate(LocalDateTime scheduledDepartureDate) {
		this.scheduledDepartureDate = scheduledDepartureDate;
	}

	public double getDepartureDelay() {
		return departureDelay;
	}

	public void setDepartureDelay(double departureDelay) {
		this.departureDelay = departureDelay;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(double elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public double getArrivalDelay() {
		return arrivalDelay;
	}

	public void setArrivalDelay(double arrivalDelay) {
		this.arrivalDelay = arrivalDelay;
	}

	
	
	
	
}
