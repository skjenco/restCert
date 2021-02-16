package com.skjenco.app.dto;

import com.skjenco.app.model.EmployeePhone;

public class EmployeePhoneDTO {
    private Long id;
    private String type;
    private String number;
    private EmployeeDataHyperlinksDTO links;

    public EmployeePhoneDTO() {
    }

    public EmployeePhoneDTO(EmployeePhone phone) {
        this(phone, "GET");
    }

    public EmployeePhoneDTO(EmployeePhone phone, String operation) {
        if(phone != null) {
            id = phone.getId();
            type = phone.getType();
            number = phone.getNumber();
            if (phone.getEmployee() != null && phone.getEmployee().getId() != null) {
                if (!"DELETE".equals(operation)) {
                    this.links = new EmployeeDataHyperlinksDTO(phone.getId(), "/employees/" + phone.getEmployee().getId() + "/phones/");
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public EmployeeDataHyperlinksDTO getLinks() {
        return links;
    }

    public void setLinks(EmployeeDataHyperlinksDTO links) {
        this.links = links;
    }
}
