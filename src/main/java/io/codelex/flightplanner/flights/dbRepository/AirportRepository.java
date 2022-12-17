package io.codelex.flightplanner.flights.dbRepository;

import io.codelex.flightplanner.flights.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
}
