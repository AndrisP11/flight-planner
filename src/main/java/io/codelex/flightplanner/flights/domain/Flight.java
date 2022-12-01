package io.codelex.flightplanner.flights.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airport_to")
    Airport to;
    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airport_from")
    Airport from;
    @Valid
    @NotBlank
    @Column(name = "carrier")
    private String carrier;
    @Valid
    @NotNull
    @Column(name = "departure")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @Valid
    @NotNull
    @Column(name = "arrival")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public Flight(Airport to, Airport from, String carrier, String departureTime, String arrivalTime) {
        this.id = getId();
        this.to = to;
        this.from = from;
        this.carrier = carrier;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        this.departureTime = LocalDateTime.parse(departureTime, formatter);
        this.arrivalTime = LocalDateTime.parse(arrivalTime, formatter);
    }

    public Flight() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;
        return getId().equals(flight.getId()) && getTo().equals(flight.getTo()) && getFrom().equals(flight.getFrom()) && getCarrier().equals(flight.getCarrier()) && getDepartureTime().equals(flight.getDepartureTime()) && getArrivalTime().equals(flight.getArrivalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTo(), getFrom(), getCarrier(), getDepartureTime(), getArrivalTime());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", to=" + to +
                ", from=" + from +
                ", carrier='" + carrier + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    public boolean areFlightsEqual(Flight anotherFlight) {
        return getFrom().getCountry().equals(anotherFlight.getFrom().getCountry()) &&
                getFrom().getCity().equals(anotherFlight.getFrom().getCity()) &&
                getFrom().getAirport().equals(anotherFlight.getFrom().getAirport()) &&
                getTo().getCountry().equals(anotherFlight.getTo().getCountry()) &&
                getTo().getCity().equals(anotherFlight.getTo().getCity()) &&
                getTo().getAirport().equals(anotherFlight.getTo().getAirport()) &&
                getCarrier().equals(anotherFlight.getCarrier()) &&
                getArrivalTime().equals(anotherFlight.getArrivalTime()) &&
                getDepartureTime().equals(anotherFlight.getDepartureTime());
    }
}
