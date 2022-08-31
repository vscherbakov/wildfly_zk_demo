package com.vvs.javaee.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
        @NamedQuery(name = Address.Query.FIND_ALL, query = "SELECT a FROM Address a ORDER BY a.city, a.street"),
})
public class Address extends BaseEntity {
    private static final long serialVersionUID = -4221152467323610512L;

    public static class Query {
        public static final String FIND_ALL = "Address.findAll";
    }

    public String getPostIndex() { return postIndex; }
    public void setPostIndex(String postIndex) {
        this.postIndex = postIndex;
    }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getBuilding() {
        return building;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
    public String getApartment() {
        return apartment;
    }
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }


    private String postIndex;

    @NotNull
    @Size(min = 1, max = 64)
    private String city;

    @NotNull
    @Size(min = 1, max = 64)
    private String street;

    @NotNull
    @Size(min = 1, max = 8)
    private String building;

    private String apartment;

}
