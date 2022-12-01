package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Airport;
import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.SearchFlight;
import io.codelex.flightplanner.flights.dto.SearchFlightResult;
import io.codelex.flightplanner.flights.forTestResult.resultAirport;
import io.codelex.flightplanner.flights.forTestResult.resultFlight;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightController {

    private FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    @GetMapping("/flights/get")
    public List<Flight> getFlights() {
        return service.getFlights();
    }

    @PostMapping("/testing-api/clear")
    public void clear() {
        service.clear();
    }

    @PutMapping("/admin-api/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public resultFlight addFlights(@Valid @RequestBody Flight flight) {
        Flight flight1 = service.addFlights(flight);

        //Because in Airport I added airport_id. Needed to add this part for tests to work correctly.
        return transformFlightToResultFlight(flight1);
    }

    @DeleteMapping(path = "/admin-api/flights/{flightId}")
    public void deleteFlights(@PathVariable String flightId) {
        int fligId = Integer.parseInt(flightId);
        service.deleteFlights(fligId);
    }

    @GetMapping(path = "/admin-api/flights/{flightId}")
    public Flight fetchFlight(@PathVariable String flightId) {
        int flighId = Integer.parseInt(flightId);
        return service.fetchFlight(flighId);
    }

    @GetMapping("/api/airports")
    public List<resultAirport> searchAirport(@RequestParam String search) {
        List<Airport> airports = service.searchAirport(search);

        //Because in Airport I added airport_id. Needed to add this part for tests to work correctly.
        resultAirport resultAirport1 = new resultAirport(airports.get(0).getCountry(), airports.get(0).getCity(), airports.get(0).getAirport());
        List<resultAirport> resultAirports = new ArrayList<>();
        resultAirports.add(resultAirport1);
        return resultAirports;
    }

    @PostMapping("/api/flights/search")
    @ResponseStatus(HttpStatus.OK)
    public SearchFlightResult searchFlight(@RequestBody SearchFlight searchFlight) {
        return service.searchFlight(searchFlight);
    }

    @GetMapping("/api/flights/{flightId}")
    public resultFlight findFlightById(@PathVariable String flightId) {
        int flighId = Integer.parseInt(flightId);
        Flight flight = service.findFlightById(flighId);

        //Because in Airport I added airport_id. Needed to add this part for tests to work correctly.
        return transformFlightToResultFlight(flight);
    }

    public resultFlight transformFlightToResultFlight(Flight flight) {
        resultAirport resultAirportTo = new resultAirport(flight.getTo().getCountry(), flight.getTo().getCity(), flight.getTo().getAirport());
        resultAirport resultAirportFrom = new resultAirport(flight.getFrom().getCountry(), flight.getFrom().getCity(), flight.getFrom().getAirport());
        return new resultFlight(flight.getId(), resultAirportTo, resultAirportFrom, flight.getCarrier(), flight.getDepartureTime(), flight.getArrivalTime());
    }
}
