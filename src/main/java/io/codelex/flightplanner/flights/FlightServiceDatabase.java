package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Airport;
import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.SearchFlight;
import io.codelex.flightplanner.flights.dto.SearchFlightResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "myapp", name = "appmode", havingValue = "database")
public class FlightServiceDatabase implements FlightService {

    @Autowired
    private FlightRepositoryDatabase flightRepositoryDatabase;

    public List<Flight> getFlights() {
        return new ArrayList<>(flightRepositoryDatabase.findByIdNotNull());
    }

    public synchronized Flight addFlights(Flight flight) {
        checkIfSame(flight);
        checkSameAirport(flight);
        checkDate(flight);
        flightRepositoryDatabase.save(flight);
        return flight;
    }

    private void checkIfSame(Flight flight) {
        if (getFlights().stream().anyMatch(flight::areFlightsEqual)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void checkSameAirport(Flight flight) {
        if (flight.getFrom().areAirportsEqual(flight.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void checkDate(Flight flight) {
        if (!flight.getArrivalTime().isAfter(flight.getDepartureTime()) ||
                flight.getArrivalTime().isEqual(flight.getDepartureTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void clear() {
        flightRepositoryDatabase.deleteAll();
    }


    public void deleteFlights(int flightId) {
        List<Flight> tempList = getFlights().stream().filter(flight -> flight.getId() == flightId).toList();
        if (tempList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.OK);
        } else {
            flightRepositoryDatabase.delete(tempList.get(0));
        }

    }


    public Flight fetchFlight(int flightId) {
        Long searchFlightID = (long) flightId;
        Optional<Flight> tempList = flightRepositoryDatabase.findById(searchFlightID);
        if (tempList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return tempList.get();
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
        return getFlights().stream().filter(flight -> flight.getFrom().getAirport().toUpperCase().startsWith(phrase) ||
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
        return getFlights().stream()
                .filter(flight -> flight.getFrom().getAirport().equalsIgnoreCase(searchFlight.getFrom()) &&
                        flight.getTo().getAirport().equalsIgnoreCase(searchFlight.getTo()) &&
                        flight.getDepartureTime().toString().matches(searchFlight.getDepartureDate().toString())).toList();
    }

    public Flight findFlightById(int flightId) {
        Long searchFlightID = (long) flightId;
        Optional<Flight> tempList = flightRepositoryDatabase.findById(searchFlightID);
        if (tempList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return tempList.get();
        }
    }
}
