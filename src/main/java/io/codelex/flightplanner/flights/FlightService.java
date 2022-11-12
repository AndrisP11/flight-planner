package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Airport;
import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.domain.SearchFlight;
import io.codelex.flightplanner.flights.domain.SearchFlightResult;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FlightService {

    Flight addFlights(Flight flight);

    List<Flight> getFlights();

    void clear();

    void deleteFlights(int flightId);

    Flight fetchFlight(int flightId);

    List<Airport> searchAirport(String search);

    SearchFlightResult searchFlight(SearchFlight searchFlight);

    Flight findFlightById(int flightId);
}
