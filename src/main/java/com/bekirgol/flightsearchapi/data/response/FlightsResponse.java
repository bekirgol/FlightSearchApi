package com.bekirgol.flightsearchapi.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class FlightsResponse {
    private String id;
    private long departureAirport;
    private long arrivalAirport;
    private Date departureDate;
    private Date arrivalDate;
    private long price;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("departureAirport")
    public long getDepartureAirport() { return departureAirport; }
    @JsonProperty("departureAirport")
    public void setDepartureAirport(long value) { this.departureAirport = value; }

    @JsonProperty("arrivalAirport")
    public long getArrivalAirport() { return arrivalAirport; }
    @JsonProperty("arrivalAirport")
    public void setArrivalAirport(long value) { this.arrivalAirport = value; }

    @JsonProperty("departureDate")
    public Date getDepartureDate() { return departureDate; }
    @JsonProperty("departureDate")
    public void setDepartureDate(Date value) { this.departureDate = value; }

    @JsonProperty("arrivalDate")
    public Date getArrivalDate() { return arrivalDate; }
    @JsonProperty("arrivalDate")
    public void setArrivalDate(Date value) { this.arrivalDate = value; }

    @JsonProperty("price")
    public long getPrice() { return price; }
    @JsonProperty("price")
    public void setPrice(long value) { this.price = value; }
}
