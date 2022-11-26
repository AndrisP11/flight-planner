package io.codelex.flightplanner.flights.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class SearchFlight {
    @Valid
    @NotNull
    private String from;
    @Valid
    @NotNull
    private String to;
    @Valid
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    public SearchFlight(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        this.departureDate = LocalDate.parse(departureDate, formatter);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchFlight that)) return false;
        return getFrom().equals(that.getFrom()) && getTo().equals(that.getTo()) && getDepartureDate().equals(that.getDepartureDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getDepartureDate());
    }

    @Override
    public String toString() {
        return "SearchFlight{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", departureDate=" + departureDate +
                '}';
    }
}
