package com.skjenco.app.dto;

import com.skjenco.app.model.EmployeeSalary;

public class EmployeeSalaryDTO {
    private Long id;
    private Long salary;
    private EmployeeDataHyperlinksDTO links;

    public EmployeeSalaryDTO() {
    }

    public EmployeeSalaryDTO(EmployeeSalary employeeSalary) {
        this(employeeSalary, "GET");
    }

    public EmployeeSalaryDTO(EmployeeSalary employeeSalary, String operation) {
        if(employeeSalary != null) {
            id = employeeSalary.getId();
            salary = employeeSalary.getSalary();
            if (employeeSalary.getEmployee() != null && employeeSalary.getEmployee().getId() != null) {
                if (!"DELETE".equals(operation)) {
                    this.links = new EmployeeDataHyperlinksDTO(employeeSalary.getId(), "/employees/" + employeeSalary.getEmployee().getId() + "/salaries/");
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

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public EmployeeDataHyperlinksDTO getLinks() {
        return links;
    }

    public void setLinks(EmployeeDataHyperlinksDTO links) {
        this.links = links;
    }
}
