package io.codelex.flightplanner.flights.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer airport_id;
    @Valid
    @NotBlank
    @Column(name = "country")
    private String country;
    @Valid
    @NotBlank
    @Column(name = "city")
    private String city;
    @Valid
    @NotBlank
    @Column(name = "airport")
    private String airport;

    public Airport(String country, String city, String airport) {
        this.airport_id = getAirport_id();
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    public Airport() {

    }

    public Integer getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(Integer airport_id) {
        this.airport_id = airport_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport1)) return false;
        return getAirport_id().equals(airport1.getAirport_id()) && getCountry().equals(airport1.getCountry()) && getCity().equals(airport1.getCity()) && getAirport().equals(airport1.getAirport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAirport_id(), getCountry(), getCity(), getAirport());
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airport_id=" + airport_id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", airport='" + airport + '\'' +
                '}';
    }

    public boolean areAirportsEqual(Airport anotherAirport) {
        return getCountry().trim().equalsIgnoreCase(anotherAirport.getCountry().trim()) &&
                getCity().trim().equalsIgnoreCase(anotherAirport.getCity().trim()) &&
                getAirport().trim().equalsIgnoreCase(anotherAirport.getAirport().trim());
    }
}
