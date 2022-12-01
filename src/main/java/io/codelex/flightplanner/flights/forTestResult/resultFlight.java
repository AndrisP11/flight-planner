package io.codelex.flightplanner.flights.forTestResult;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class resultFlight {
    private Integer id;
    private resultAirport to;
    private resultAirport from;
    private String carrier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public resultFlight(Integer id, resultAirport to, resultAirport from, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public resultAirport getTo() {
        return to;
    }

    public void setTo(resultAirport to) {
        this.to = to;
    }

    public resultAirport getFrom() {
        return from;
    }

    public void setFrom(resultAirport from) {
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
        if (!(o instanceof resultFlight that)) return false;
        return getId().equals(that.getId()) && getTo().equals(that.getTo()) && getFrom().equals(that.getFrom()) && getCarrier().equals(that.getCarrier()) && getDepartureTime().equals(that.getDepartureTime()) && getArrivalTime().equals(that.getArrivalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTo(), getFrom(), getCarrier(), getDepartureTime(), getArrivalTime());
    }

    @Override
    public String toString() {
        return "resultFlight{" +
                "id=" + id +
                ", to=" + to +
                ", from=" + from +
                ", carrier='" + carrier + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
