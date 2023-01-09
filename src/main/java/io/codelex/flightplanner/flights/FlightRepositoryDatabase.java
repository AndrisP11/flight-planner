package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.domain.Flight;
import org.h2.command.query.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.From;
import java.util.List;

@Repository
public interface FlightRepositoryDatabase extends JpaRepository<Flight, Long> {
    List<Flight> findByIdNotNull();
}
