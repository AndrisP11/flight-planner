package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Airport;
import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.domain.SearchFlight;
import io.codelex.flightplanner.flights.domain.SearchFlightResult;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@ConditionalOnProperty(prefix = "myapp", name = "appmode", havingValue = "inmemory")
public class FlightServerInMemory implements FlightService {
    private FlightRepositoryInMemory repository;

    public FlightServerInMemory(FlightRepositoryInMemory repository) {
        this.repository = repository;
    }

    @Override
    public Flight addFlights(Flight flight) {
        checkIfSame(flight);
        checkIfNull(flight);
        checkSameAirport(flight);
        checkDate(flight);
        return repository.addFlights(flight);
    }

    public void checkIfSame(Flight flight) {
        List<Flight> sameFlight = repository.flights.stream().filter(flight1 ->
                flight1.getFrom().equals(flight.getFrom()) &&
                        flight1.getTo().equals(flight.getTo()) &&
                        flight1.getCarrier().equals(flight.getCarrier()) &&
                        flight1.getArrivalTime().equals(flight.getArrivalTime()) &&
                        flight1.getDepartureTime().equals(flight.getDepartureTime())
        ).toList();
        if (sameFlight.size() > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public void checkIfNull(Flight flight) {
        if (flight.getFrom() == null ||
                flight.getTo() == null ||
                flight.getFrom().getCountry() == null ||
                flight.getFrom().getCity() == null ||
                flight.getFrom().getAirport() == null ||
                flight.getTo().getCountry() == null ||
                flight.getTo().getCity() == null ||
                flight.getTo().getAirport() == null ||
                flight.getCarrier() == null ||
                flight.getDepartureTime() == null ||
                flight.getArrivalTime() == null ||
                flight.getFrom().getCountry().equals("") ||
                flight.getFrom().getCity().equals("") ||
                flight.getFrom().getAirport().equals("") ||
                flight.getTo().getCountry().equals("") ||
                flight.getTo().getCity().equals("") ||
                flight.getTo().getAirport().equals("") ||
                flight.getCarrier().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void checkSameAirport(Flight flight) {
        if (flight.getFrom().getCountry().equalsIgnoreCase(flight.getTo().getCountry()) ||
                flight.getFrom().getCity().equalsIgnoreCase(flight.getTo().getCity()) ||
                flight.getFrom().getAirport().equalsIgnoreCase(flight.getTo().getAirport())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void checkDate(Flight flight) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        LocalDateTime depart = LocalDateTime.parse(flight.getDepartureTime(), formatter);
        LocalDateTime arrive = LocalDateTime.parse(flight.getArrivalTime(), formatter);
        if (!arrive.isAfter(depart) || arrive.isEqual(depart)) {
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
