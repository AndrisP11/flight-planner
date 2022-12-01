package io.codelex.flightplanner.flights.dbRepository;

import io.codelex.flightplanner.flights.domain.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, String> {
}
