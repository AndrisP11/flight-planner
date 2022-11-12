package io.codelex.flightplanner.flights.domain;

import java.util.List;

public class SearchFlightResult {
    private int page;
    private int totalItems;
    List<Flight> items;

    public SearchFlightResult(int page, int totalItems, List<Flight> items) {
        this.page = page;
        this.totalItems = totalItems;
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<Flight> getItems() {
        return items;
    }

    public void setItems(List<Flight> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "SearchFlightResult{" +
                "page=" + page +
                ", totalItems=" + totalItems +
                ", items=" + items +
                '}';
    }
}
