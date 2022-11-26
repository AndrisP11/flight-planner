package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Airport;
import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.SearchFlight;
import io.codelex.flightplanner.flights.dto.SearchFlightResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepositoryInMemory {

    List<Flight> flights = new ArrayList<>();

    public FlightRepositoryInMemory(List<Flight> flights) {
        this.flights = flights;
    }

    public synchronized Flight addFlights(Flight flight) {
        flights.add(flight);
        return flight;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void clear() {
        flights.clear();
    }

    public synchronized void deleteFlights(int flightId) {
        List<Flight> tempList = flights.stream().filter(flight -> flight.getId() == flightId).toList();
        if (tempList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.OK);
        } else {
            flights.remove(tempList.get(0));
        }
    }

    public Flight fetchFlight(int flightId) {
        List<Flight> tempList = flights.stream().filter(flight -> flight.getId() == flightId).toList();
        if (tempList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return tempList.get(0);
        }
    }

    public List<Airport> searchAirport(String search) {
        String phrase = search.toUpperCase().trim();
        List<Flight> tempList = findAllAirports(phrase);
        if (tempList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.OK);
        } else {
            Flight foundAirport = tempList.get(0);
            Airport port = new Airport(foundAirport.getFrom().getCountry(), foundAirport.getFrom().getCity(), foundAirport.getFrom().getAirport());
            List<Airport> airports = new ArrayList<>();
            airports.add(port);
            return airports;
        }
    }

    private List<Flight> findAllAirports(String phrase) {
        return flights.stream().filter(flight -> flight.getFrom().getAirport().toUpperCase().startsWith(phrase) ||
                flight.getFrom().getCity().toUpperCase().startsWith(phrase) ||
                flight.getFrom().getCountry().toUpperCase().startsWith(phrase) ||
                flight.getTo().getAirport().toUpperCase().startsWith(phrase) ||
                flight.getTo().getCity().toUpperCase().startsWith(phrase) ||
                flight.getTo().getCountry().toUpperCase().startsWith(phrase)).toList();
    }

    public SearchFlightResult searchFlight(SearchFlight searchFlight) {
        if (isSearchFlightNull(searchFlight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            List<Flight> tempList = findSearchedFlights(searchFlight);
            if (tempList.size() == 0) {
                int page = 0;
                int totalItems = 0;
                return new SearchFlightResult(page, totalItems, tempList);
            } else {
                int page = tempList.size();
                int totalItems = tempList.size();
                return new SearchFlightResult(page, totalItems, tempList);
            }
        }
    }

    private boolean isSearchFlightNull(SearchFlight searchFlight) {
        return searchFlight.getFrom() == null ||
                searchFlight.getTo() == null ||
                searchFlight.getDepartureDate() == null ||
                searchFlight.getFrom().equals(searchFlight.getTo());
    }

    private List<Flight> findSearchedFlights(SearchFlight searchFlight) {
        return flights.stream()
                .filter(flight -> flight.getFrom().getAirport().equalsIgnoreCase(searchFlight.getFrom()) &&
                        flight.getTo().getAirport().equalsIgnoreCase(searchFlight.getTo()) &&
                        flight.getDepartureTime().toString().matches(searchFlight.getDepartureDate().toString())).toList();
    }

    public Flight findFlightById(int flightId) {
        List<Flight> tempList = flights.stream().filter(flight -> flight.getId() == flightId).toList();
        if (tempList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return tempList.get(0);
        }
    }
}
