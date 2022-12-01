package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepositoryDatabase extends JpaRepository<Flight, Long> {

}
