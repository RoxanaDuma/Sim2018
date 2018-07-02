package it.polito.tdp.flightdelays.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.flightdelays.model.Airline;
import it.polito.tdp.flightdelays.model.Airport;
import it.polito.tdp.flightdelays.model.CoppiaAirports;
import it.polito.tdp.flightdelays.model.Flight;

public class FlightDelaysDAO {

	public List<Airline> loadAllAirlines() {
		String sql = "SELECT id, iata_code ,airline from airlines";
		List<Airline> result = new ArrayList<Airline>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Airline(rs.getInt("ID"),rs.getString("iata_code"), rs.getString("airline")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Airport> loadAllAirports(Map<Integer, Airport> airportIdMap) {
		String sql = "SELECT id,iata_code, airport, city, state, country, latitude, longitude ,timezone_offset FROM airports";
		List<Airport> result = new ArrayList<Airport>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int airportId = rs.getInt("id");
				Airport airport = new Airport(rs.getInt("id"),rs.getString("iata_code"), rs.getString("airport"), rs.getString("city"),
						rs.getString("state"), rs.getString("country"), rs.getDouble("latitude"), rs.getDouble("longitude"),rs.getDouble("timezone_offset"));
				result.add(airport);
				airportIdMap.put(airportId, airport);
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

/*	public List<Flight> loadAllFlights() {
		String sql = "SELECT id, airline, flight_number, origin_airport_id, destination_airport_id, scheduled_dep_date, "
				+ "arrival_date, departure_delay, arrival_delay, air_time, distance FROM flights";
		List<Flight> result = new LinkedList<Flight>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight(rs.getInt("id"), rs.getString("airline"), rs.getInt("flight_number"),
						rs.getString("origin_airport_id"), rs.getString("destination_airport_id"),
						rs.getTimestamp("scheduled_dep_date").toLocalDateTime(),
						rs.getTimestamp("arrival_date").toLocalDateTime(), rs.getInt("departure_delay"),
						rs.getInt("arrival_delay"), rs.getInt("air_time"), rs.getInt("distance"));
				result.add(flight);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	*/
	
	public List<Airport> getAirportsByAirline(Airline airline, Map<Integer, Airport> airportIdMap) {
		
		String sql = "select distinct airportId " + 
				"from ( " + 
				"select distinct ORIGIN_AIRPORT_ID as airportId " + 
				"from flights " + 
				"where airline_id = ? " + 
				"union " + 
				"select distinct DESTINATION_AIRPORT_ID as airportId " + 
				"from flights " + 
				"where airline_id = ? " + 
				" ) as ce " ;
		
		List<Airport> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, airline.getIdAirline());
			st.setInt(2, airline.getIdAirline());
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				result.add(airportIdMap.get(rs.getInt("airportId")));
				
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
		public List<CoppiaAirports> getCoppiaAirports(Airline airline) {
			
			String sql ="select distinct origin_airport_id,destination_airport_id " + 
					"from flights " + 
					"where airline_id= ? " + 
					"order by origin_airport_id, destination_airport_id " ;
			
			List<CoppiaAirports> result = new ArrayList<>();
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, airline.getIdAirline());
				
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					
					result.add(new CoppiaAirports(rs.getInt("origin_airport_id"),rs.getInt("destination_airport_id")));
					
				}

				conn.close();
				return result;

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore connessione al database");
				throw new RuntimeException("Error Connection Database");
			}
		}

		
		
		

		public double getMediaRitardi(Airport a1, Airport a2, Airline airline) {
			
			String sql = "select avg(arrival_delay) as m " + 
					"from flights " + 
					"where origin_airport_id = ? " + 
					"and destination_airport_id = ? " + 
					"and airline_id = ? " ;
			
			double mediaR=0.0;
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, a1.getIdAirport());
				st.setInt(2, a2.getIdAirport());
				st.setInt(3, airline.getIdAirline());
				
				ResultSet rs = st.executeQuery();

				//rs.first();
				while(rs.next()) {
					mediaR = rs.getDouble("m");
				}
				
				
				conn.close();
				return mediaR;

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore connessione al database");
				throw new RuntimeException("Error Connection Database");
			}
		}
	
	
	
	
	
}

