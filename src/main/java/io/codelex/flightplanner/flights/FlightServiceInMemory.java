package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Airport;
import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.SearchFlight;
import io.codelex.flightplanner.flights.dto.SearchFlightResult;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "myapp", name = "appmode", havingValue = "inmemory")
public class FlightServiceInMemory implements FlightService {
    private FlightRepositoryInMemory repository;

    public FlightServiceInMemory(FlightRepositoryInMemory repository) {
        this.repository = repository;
    }

    @Override
    public synchronized Flight addFlights(Flight flight) {
        checkIfSame(flight);
        checkSameAirport(flight);
        checkDate(flight);
        return repository.addFlights(flight);
    }

    private void checkIfSame(Flight flight) {
        if (repository.flights.stream().anyMatch(flight::areFlightsEqual)) {
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

    @Override
    public List<Flight> getFlights() {
        return repository.getFlights();
    }

    @Override
    public void clear() {
        repository.clear();
    }

    @Override
    public void deleteFlights(int flightId) {
        repository.deleteFlights(flightId);
    }

    @Override
    public Flight fetchFlight(int flightId) {
        return repository.fetchFlight(flightId);
    }

    @Override
    public List<Airport> searchAirport(String search) {
        return repository.searchAirport(search);
    }

    @Override
    public SearchFlightResult searchFlight(SearchFlight searchFlight) {
        return repository.searchFlight(searchFlight);
    }

    @Override
    public Flight findFlightById(int flightId) {
        return repository.findFlightById(flightId);
    }


}
