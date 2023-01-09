package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Airport;
import io.codelex.flightplanner.flights.domain.Flight;
import io.codelex.flightplanner.flights.dto.SearchFlight;
import io.codelex.flightplanner.flights.dto.SearchFlightResult;
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
    public Flight addFlights(@Valid @RequestBody Flight flight) {
        return service.addFlights(flight);
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
    public List<Airport> searchAirport(@RequestParam String search) {
        return service.searchAirport(search);
    }

    @PostMapping("/api/flights/search")
    @ResponseStatus(HttpStatus.OK)
    public SearchFlightResult searchFlight(@RequestBody SearchFlight searchFlight) {
        return service.searchFlight(searchFlight);
    }

    @GetMapping("/api/flights/{flightId}")
    public Flight findFlightById(@PathVariable String flightId) {
        int flighId = Integer.parseInt(flightId);
        return service.findFlightById(flighId);
    }
}
