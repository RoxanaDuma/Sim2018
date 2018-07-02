package it.polito.tdp.flightdelays.model;

public class AirportAndWeight implements Comparable<AirportAndWeight> {

	private Airport origin;
	private Airport destination;
	private double weight ;
	
	public AirportAndWeight(Airport origin, Airport destination, double weight) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(AirportAndWeight other) {
		
		//return -(int)(this.weight-other.getWeight());
		return -(Double.compare(weight, other.getWeight()));
	}
	
	
	@Override
	public String toString() {
		return this.origin.getName()+" - "+this.destination.getName()+" con peso : "+this.weight+"\n";
	}
	
}
