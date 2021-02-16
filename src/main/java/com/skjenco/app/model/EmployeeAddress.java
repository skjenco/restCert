package com.skjenco.app.model;

import com.skjenco.app.dto.EmployeeAddressDTO;

import javax.persistence.*;

@Entity
@Table(name = "employee_address")
public class EmployeeAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    protected EmployeeAddress() {}

    public EmployeeAddress(EmployeeAddressDTO addressDTO, Long employeeId) {
        this(addressDTO);
        this.employee = new Employee(employeeId);
    }

    public EmployeeAddress(EmployeeAddressDTO addressDTO) {
        this.id = addressDTO.getId();
        this.streetAddress = addressDTO.getStreetAddress();
        this.city = addressDTO.getCity();
        this.state = addressDTO.getState();
        this.country = addressDTO.getCountry();
        this.postalCode = addressDTO.getPostalCode();
    }

    @Override
    public String toString() {
        return String.format(
                "Org[id=%d, employeeId='%d', streetAddress='%s', city='%s', state='%s', country='%s', postalCode='%s']",
                id, employee.getId(), streetAddress, city, state, country, postalCode);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
}
