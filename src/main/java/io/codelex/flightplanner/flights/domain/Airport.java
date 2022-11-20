package io.codelex.flightplanner.flights.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Airport {
    @Valid
    @NotBlank
    private String country;
    @Valid
    @NotBlank
    private String city;
    @Valid
    @NotBlank
    private String airport;

    public Airport(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
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
        return getCountry().equals(airport1.getCountry()) && getCity().equals(airport1.getCity()) && getAirport().equals(airport1.getAirport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCity(), getAirport());
    }

    @Override
    public String toString() {
        return "Airport{" +
                "country='" + country + '\'' +
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
