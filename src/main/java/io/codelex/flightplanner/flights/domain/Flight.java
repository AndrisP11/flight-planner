package io.codelex.flightplanner.flights.domain;

import java.util.Objects;

public class Flight {
    private static int count = 0;
    private int id;
    Airport to;
    Airport from;
    private String carrier;

    private String departureTime;

    private String arrivalTime;


    public Flight(Airport to, Airport from, String carrier, String departureTime, String arrivalTime) {
        this.id = count++;
        this.to = to;
        this.from = from;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", to=" + to +
                ", from=" + from +
                ", carrier='" + carrier + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;
        return getId() == flight.getId() && getTo().equals(flight.getTo()) && getFrom().equals(flight.getFrom()) && getCarrier().equals(flight.getCarrier()) && getDepartureTime().equals(flight.getDepartureTime()) && getArrivalTime().equals(flight.getArrivalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTo(), getFrom(), getCarrier(), getDepartureTime(), getArrivalTime(), getId());
    }
}
