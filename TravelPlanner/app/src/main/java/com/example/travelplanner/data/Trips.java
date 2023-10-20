package com.example.travelplanner.data;

public class Trips {
    private String sourceCity;
    private String sourceCountry;
    private String destinationCity;
    private String destinationCountry;
    private String Date;

    public Trips(String sourceCity,String sourceCountry,String destinationCity,String destinationCountry,String date){
        this.sourceCity = sourceCity;
        this.sourceCountry = sourceCountry;
        this.destinationCity = destinationCity;
        this.destinationCountry = destinationCountry;
        this.Date = date;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public String getSourceCountry() {
        return sourceCountry;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }
    public String getTripDate() {
        return Date;
    }
}
