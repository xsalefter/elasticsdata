package com.xsalefter.elasticsdata.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "restaurants")
public class Restaurant implements Serializable {

    private static final long serialVersionUID = -6362946137806788285L;

    @Id
    private String id;

    @Column(name = "restaurant_id")
    private String restaurantId;

    @Column(name = "name")
    private String name;

    @Column(name = "borough")
    private String borough;

    @Column(name = "cuisine")
    private String cuisine;

    @Field(type = FieldType.Nested)
    private Address address;

    @Field(type = FieldType.Nested)
    private Set<Grade> grades;

    public Restaurant() {
    }

    public Restaurant(String restaurantId, String name, String borough, String cuisine) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.borough = borough;
        this.cuisine = cuisine;
    }

    public Restaurant(String restaurantId, String name, String borough, String cuisine, Address address, Set<Grade> grades) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.borough = borough;
        this.cuisine = cuisine;
        this.address = address;
        this.grades = grades;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Restaurant [id=").append(id).append(", restaurantId=").append(restaurantId).append(", name=")
                .append(name).append(", borough=").append(borough).append(", cuisine=").append(cuisine)
                .append(", address=").append(address).append(", grades=").append(grades).append("]");
        return builder.toString();
    }

}
