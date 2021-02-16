package com.skjenco.app.dto;

import com.skjenco.app.model.EmployeeAddress;

public class EmployeeAddressDTO {
    private Long id;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private EmployeeDataHyperlinksDTO links;

    public EmployeeAddressDTO() {
    }

    public EmployeeAddressDTO(EmployeeAddress address) {
        this(address, "GET");
    }

    public EmployeeAddressDTO(EmployeeAddress address, String operation) {
        if(address != null) {
            id = address.getId();
            streetAddress = address.getStreetAddress();
            city = address.getCity();
            state = address.getState();
            country = address.getCountry();
            postalCode = address.getPostalCode();
            if (address.getEmployee() != null && address.getEmployee().getId() != null) {
                if (!"DELETE".equals(operation)) {
                    this.links = new EmployeeDataHyperlinksDTO(address.getId(), "/employees/" + address.getEmployee().getId() + "/addresses/");
                }
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public EmployeeDataHyperlinksDTO getLinks() {
        return links;
    }

    public void setLinks(EmployeeDataHyperlinksDTO links) {
        this.links = links;
    }
}
