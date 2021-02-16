package com.skjenco.app.dto;

import com.skjenco.app.model.Employee;

import java.util.List;

public class OrgChartDTO {
    private Long id;
    private String name;
    private List<OrgChartDTO> reports;
    private EmployeeHyperlinksDTO employeeHyperlinksDTO;

    public OrgChartDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public OrgChartDTO(EmployeeDTO employeeDTO) {
        this.id = employeeDTO.getId();
        this.name = employeeDTO.getName();
        this.employeeHyperlinksDTO = new EmployeeHyperlinksDTO(employeeDTO.getId());
    }

    public OrgChartDTO(Employee e) {
        id = e.getId();
        name = e.getName();
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

    public List<OrgChartDTO> getReports() {
        return reports;
    }

    public void setReports(List<OrgChartDTO> reports) {
        this.reports = reports;
    }

    public EmployeeHyperlinksDTO getEmployeeHyperlinksDTO() {
        return employeeHyperlinksDTO;
    }

    public void setEmployeeHyperlinksDTO(EmployeeHyperlinksDTO employeeHyperlinksDTO) {
        this.employeeHyperlinksDTO = employeeHyperlinksDTO;
    }
}
