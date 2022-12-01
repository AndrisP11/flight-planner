package io.codelex.flightplanner.flights.dbRepository;

import io.codelex.flightplanner.flights.domain.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
}
