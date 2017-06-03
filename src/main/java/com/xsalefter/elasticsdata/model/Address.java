package com.xsalefter.elasticsdata.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;

import org.springframework.data.annotation.Transient;

public class Address implements Serializable {

    private static final long serialVersionUID = 123510929144428989L;
    public static final short LAT = 0;
    public static final short LONG = 1;

    @Column(name = "building")
    private String building;

    @Column(name = "street")
    private String street;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "coord")
    private Double[] coord = new Double[2];

    public Address() {
    }

    public Address(String building, String street, String zipCode, Double latitude, Double longitude) {
        this.building = building;
        this.street = street;
        this.zipCode = zipCode;
        this.coord[LAT] = latitude;
        this.coord[LONG] = longitude;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Double[] getCoord() {
        return this.coord;
    }

    public void setCoord(Double[] coord) {
        this.coord = coord;
    }

    @Transient
    public void setLatitude(Double latitude) {
        this.coord[LAT] = latitude;
    }

    @Transient
    public void setLongitude(Double longitude) {
        this.coord[LONG] = longitude;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Address [building=").append(building).append(", street=").append(street).append(", zipCode=")
                .append(zipCode).append(", coord=").append(Arrays.toString(coord)).append("]");
        return builder.toString();
    }
}
