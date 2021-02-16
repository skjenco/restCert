package com.skjenco.app.dto;

import com.skjenco.app.model.Employee;

public class EmployeeDTO {
    private Long id;
    private String name;
    private Long supervisorId;
    private EmployeeHyperlinksDTO links;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, Long supervisorId) {
        this.name = name;
    }

    public EmployeeDTO(Employee e, String operation) {
        this(e);
        if(!"DELETE".equals(operation) && e!= null) {
            this.links = new EmployeeHyperlinksDTO(e.getId());
        }
    }

    public EmployeeDTO(Employee e) {
        if(e != null) {
            id = e.getId();
            name = e.getName();
            if(e.getOrg() != null) {
                supervisorId = e.getOrg().getSupervisorId();
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public EmployeeHyperlinksDTO getLinks() {
        return links;
    }

    public void setLinks(EmployeeHyperlinksDTO links) {
        this.links = links;
    }
}
